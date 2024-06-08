package com.ecommerce.user.infrastructure.mapper;

import com.ecommerce.user.domain.entity.ProfileType;
import com.ecommerce.user.domain.entity.User;
import com.ecommerce.user.infrastructure.schema.UserSchema;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta")
public interface UserSchemaMapper {

    User userSchemaToUser(UserSchema userSchema);
    UserSchema userToUserSchema(User user);

    default String fromObjectId(ObjectId id) {
        return id == null ? "" : id.toString();
    }

    default ObjectId toObjectId(String id) {
        return id == null ? null : new ObjectId(id);
    }

    default ProfileType toProfileType(String profileType) {
        return profileType == null ? null : ProfileType.valueOf(profileType);
    }

    default String fromPaymentStatus(ProfileType profileType) {
        return profileType == null ? "" : profileType.toString();
    }

}
