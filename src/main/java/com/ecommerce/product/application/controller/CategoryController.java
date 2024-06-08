package com.ecommerce.product.application.controller;

import com.ecommerce.product.application.dto.CategoryGenericDto;
import com.ecommerce.product.application.dto.CategoryPersistRequestDto;
import com.ecommerce.product.application.mapper.CategoryMapper;
import com.ecommerce.product.domain.entity.Category;
import com.ecommerce.product.domain.service.CategoryService;
import com.google.gson.Gson;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;

@Path("/categories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoryController {

    @Inject
    CategoryService categoryService;

    @Inject
    CategoryMapper categoryMapper;

    @GET
    public Response getAllCategories() {
        try {
            List<CategoryGenericDto> categories = categoryService.getAll()
                    .stream()
                    .map(category -> categoryMapper.categoryToCategoryDto(category))
                    .toList();
            return Response.ok(categories).build();
        } catch (Exception e) {
            return Response.serverError().entity(new Gson().toJson(e.getMessage())).build();
        }
    }

    @GET
    @Path("{id}")
    public Response getCategoryById(@PathParam("id") String id) {
        try {
            Category category = categoryService.getById(id);
            if (category == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(categoryMapper.categoryToCategoryDto(category)).build();
        } catch (Exception e) {
            return Response.serverError().entity(new Gson().toJson(e.getMessage())).build();
        }
    }

    @POST
    public Response createCategory(CategoryPersistRequestDto categoryDto, @Context UriInfo uriInfo) {
        try {
            Category category = categoryMapper.categoryPostRequestDtoToCategory(categoryDto);
            String categoryGeneratedId = categoryService.save(category);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(categoryGeneratedId);
            return Response.created(uriBuilder.build()).build();
        } catch (Exception e) {
            return Response.serverError().entity(new Gson().toJson(e.getMessage())).build();
        }
    }

    @PUT
    @Path("{id}")
    public Response updateCategory(@PathParam("id") String id, CategoryPersistRequestDto requestDto) {
        try {
            Category category = categoryService.getById(id);
            if (category == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            category.setDescription(requestDto.getDescription());
            category.setName(requestDto.getName());
            categoryService.merge(category);
            return Response.ok(categoryMapper.categoryToCategoryDto(category)).build();
        } catch (Exception e) {
            return Response.serverError().entity(new Gson().toJson(e.getMessage())).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteCategory(@PathParam("id") String id) {
        try {
            categoryService.remove(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.serverError().entity(new Gson().toJson(e.getMessage())).build();
        }
    }

}
