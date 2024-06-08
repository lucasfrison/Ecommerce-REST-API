package com.ecommerce.user.business.application.controller;

import com.ecommerce.user.authentication.application.encryption.PasswordHasher;
import com.ecommerce.user.business.application.dto.UserGenericDto;
import com.ecommerce.user.business.application.dto.UserPersistRequestDto;
import com.ecommerce.user.business.application.dto.UserUpdateRequestDto;
import com.ecommerce.user.business.application.mapper.UserMapper;
import com.ecommerce.user.business.domain.entity.User;
import com.ecommerce.user.business.domain.service.UserService;
import com.google.gson.Gson;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;

    @Inject
    UserMapper userMapper;

    @GET
    public Response getAllUsers() {
        try {
            List<UserGenericDto> users = userService.getAll()
                    .stream()
                    .map(user -> userMapper.userToUserDto(user))
                    .toList();
            return Response.ok(users).build();
        } catch (Exception e) {
            return Response.serverError().entity(new Gson().toJson(e.getMessage())).build();
        }
    }

    @GET
    @Path("{id}")
    public Response getUserById(@PathParam("id") String id) {
        try {
            User user = userService.getById(id);
            if (user == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(userMapper.userToUserDto(user)).build();
        } catch (Exception e) {
            return Response.serverError().entity(new Gson().toJson(e.getMessage())).build();
        }
    }

    @GET
    @Path("name/{name}")
    public Response getUserByName(@PathParam("name") String name) {
        try {
            User user = userService.getByName(name);
            if (user == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(userMapper.userToUserDto(user)).build();
        } catch (Exception e) {
            return Response.serverError().entity(new Gson().toJson(e.getMessage())).build();
        }
    }

    @GET
    @Path("email/{email}")
    public Response getUserByEmail(@PathParam("email") String email) {
        try {
            User user = userService.getByEmail(email);
            if (user == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(userMapper.userToUserDto(user)).build();
        } catch (Exception e) {
            return Response.serverError().entity(new Gson().toJson(e.getMessage())).build();
        }
    }

    @POST
    public Response createUser(UserPersistRequestDto userDto, @Context UriInfo uriInfo) {
        try {
            userDto.setPassword(PasswordHasher.encode(userDto.getPassword()));
            User user = userMapper.userPostRequestDtoToUser(userDto);
            String userGeneratedId = userService.save(user);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(userGeneratedId);
            return Response.created(uriBuilder.build()).build();
        } catch (Exception e) {
            return Response.serverError().entity(new Gson().toJson(e.getMessage())).build();
        }
    }

    @PUT
    @Path("{id}")
    public Response updateUser(@PathParam("id") String id, UserUpdateRequestDto requestDto) {
        try {
            User user = userService.getById(id);
            if (user == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            user.setAddress(requestDto.getAddress());
            user.setPhone(requestDto.getPhone());
            user.setProfilePicture(requestDto.getProfilePicture());
            userService.merge(user);
            return Response.ok(userMapper.userToUserDto(user)).build();
        } catch (Exception e) {
            return Response.serverError().entity(new Gson().toJson(e.getMessage())).build();
        }
    }

}
