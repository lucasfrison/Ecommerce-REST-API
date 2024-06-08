package com.ecommerce.user.infrastructure.schema;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@MongoEntity(collection = "users")
@Getter
@Setter
public class UserSchema extends PanacheMongoEntity {

    private ObjectId id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String profilePicture;
    private String profileType;

}
