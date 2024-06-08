package com.ecommerce.user.authentication.application.controller;

import com.ecommerce.user.authentication.application.dto.LoginDto;
import com.ecommerce.user.business.application.mapper.UserMapper;
import com.ecommerce.user.business.domain.entity.User;
import com.ecommerce.user.business.domain.exception.UserBusinessException;
import com.ecommerce.user.business.domain.service.UserService;
import com.google.gson.Gson;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.*;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthenticationController {

    @Inject
    UserService userService;

    @Inject
    UserMapper userMapper;

    @POST
    public Response login(LoginDto loginRequest) {
        try {
            User user = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
            return Response.ok(userMapper.userToUserDto(user)).build();
        } catch (UserBusinessException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(new Gson().toJson(e.getMessage())).build();
        } catch (Exception e) {
            return Response.serverError().entity(new Gson().toJson(e.getMessage())).build();
        }
    }

}
