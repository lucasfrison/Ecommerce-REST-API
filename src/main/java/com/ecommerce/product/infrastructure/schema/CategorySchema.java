package com.ecommerce.product.infrastructure.schema;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@MongoEntity(collection = "Categories")
@Getter
@Setter
public class CategorySchema extends PanacheMongoEntity {

    private ObjectId id;
    private String name;
    private String description;

}