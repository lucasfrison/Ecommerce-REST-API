package com.ecommerce.product.application.controller;

import com.ecommerce.product.application.dto.CategoryPersistRequestDto;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.containsString;
import static io.restassured.RestAssured.given;

@QuarkusTest
class CategoryControllerTest {

    @Test
    @Order(1)
    void shouldCreateCategory() {
        final CategoryPersistRequestDto requestDto = new CategoryPersistRequestDto();
        requestDto.setName("test");
        requestDto.setDescription("description");

        given()
                .body(requestDto)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when()
                .post("/categories")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .header("Location", containsString("http://localhost:8081/categories"));
    }

    @Test
    @Order(2)
    void shouldGetAllCategories() {
        given()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when()
                .get("/categories")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
    }

}