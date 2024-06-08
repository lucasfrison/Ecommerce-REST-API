package com.ecommerce.product.infrastructure.schema;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.List;

@MongoEntity(collection = "Products")
@Getter
@Setter
public class ProductSchema extends PanacheMongoEntity {

    private ObjectId id;
    private String name;
    private String description;
    private Double price;
    private ObjectId categoryId;
    private List<String> images;

}
