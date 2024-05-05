package com.ecommerce.product.application.controller;

import com.ecommerce.product.application.dto.ProductPersistRequestDto;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ProductControllerTest {

    private static ProductPersistRequestDto dto;

    @BeforeAll
    public static void setup() {
        dto = new ProductPersistRequestDto();
        dto.setName("test");
        dto.setDescription("description");
        dto.setPrice(150.00);
        dto.setCategoryId("6638020b6abb58742e13d047");
    }

    @Test
    @Order(5)
    void shouldCreateProduct() {
        given()
                .body(dto)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when()
                .post("/products")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .header("Location", containsString("http://localhost:8081/products"));
    }

    @Test
    @Order(6)
    void shouldGetAllProducts() {
        given()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when()
                .get("/products")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    @Order(7)
    void shouldGetProductsByCategoryId() {
        given()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when()
                .get("/products/category/6638020b6abb58742e13d047")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    @Order(8)
    void shouldGetProductByName() {
        given()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when()
                .get("/products/name/test")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("name", is("test"))
                .body("description", is("description"))
                .body("price", is(150.0F));
    }

}
