package com.ecommerce.product.application.controller;

import com.ecommerce.product.application.dto.ProductGenericDto;
import com.ecommerce.product.application.dto.ProductPersistRequestDto;
import com.ecommerce.product.application.mapper.ProductMapper;
import com.ecommerce.product.domain.entity.Product;
import com.ecommerce.product.domain.service.ProductService;
import com.google.gson.Gson;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {

    @Inject
    ProductService productService;

    @Inject
    ProductMapper productMapper;

    @GET
    public Response getAllProducts() {
        try {
            List<ProductGenericDto> products = productService.getAll()
                    .stream()
                    .map(product -> productMapper.productToProductDto(product))
                    .toList();
            return Response.ok(products).build();
        } catch (Exception e) {
            return Response.serverError().entity(new Gson().toJson(e.getMessage())).build();
        }
    }

    @GET
    @Path("{id}")
    public Response getProductById(@PathParam("id") String id) {
        try {
            Product product = productService.getById(id);
            if (product == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(productMapper.productToProductDto(product)).build();
        } catch (Exception e) {
            return Response.serverError().entity(new Gson().toJson(e.getMessage())).build();
        }
    }

    @GET
    @Path("name/{name}")
    public Response getProductByName(@PathParam("name") String name) {
        try {
            Product product = productService.getByName(name);
            if (product == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(productMapper.productToProductDto(product)).build();
        } catch (Exception e) {
            return Response.serverError().entity(new Gson().toJson(e.getMessage())).build();
        }
    }

    @GET
    @Path("category/{categoryId}")
    public Response getProductsByCategoryId(@PathParam("categoryId") String categoryId) {
        try {
            List<ProductGenericDto> products = productService.getByCategory(categoryId)
                    .stream()
                    .map(product -> productMapper.productToProductDto(product))
                    .toList();
            return Response.ok(products).build();
        } catch (Exception e) {
            return Response.serverError().entity(new Gson().toJson(e.getMessage())).build();
        }
    }

    @POST
    public Response createProduct(ProductPersistRequestDto productDto, @Context UriInfo uriInfo) {
        try {
            Product product = productMapper.productPostRequestDtoToProduct(productDto);
            String productGeneratedId = productService.save(product);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(productGeneratedId);
            return Response.created(uriBuilder.build()).build();
        } catch (Exception e) {
            return Response.serverError().entity(new Gson().toJson(e.getMessage())).build();
        }
    }

    @PUT
    @Path("{id}")
    public Response updateProduct(@PathParam("id") String id, ProductPersistRequestDto requestDto) {
        try {
            Product product = productService.getById(id);
            if (product == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            product.setDescription(requestDto.getDescription());
            product.setName(requestDto.getName());
            product.setPrice(requestDto.getPrice());
            product.setCategoryId(requestDto.getCategoryId());
            product.setImages(requestDto.getImages());
            productService.merge(product);
            return Response.ok(productMapper.productToProductDto(product)).build();
        } catch (Exception e) {
            return Response.serverError().entity(new Gson().toJson(e.getMessage())).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteProduct(@PathParam("id") String id) {
        try {
            productService.remove(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.serverError().entity(new Gson().toJson(e.getMessage())).build();
        }
    }

}
