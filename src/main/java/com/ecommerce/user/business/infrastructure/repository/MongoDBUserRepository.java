package com.ecommerce.user.business.infrastructure.repository;

import com.ecommerce.user.business.domain.entity.User;
import com.ecommerce.user.business.domain.repository.UserRepository;
import com.ecommerce.user.business.infrastructure.mapper.UserSchemaMapper;
import com.ecommerce.user.business.infrastructure.schema.UserSchema;
import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.List;

@ApplicationScoped
public class MongoDBUserRepository implements UserRepository, PanacheMongoRepositoryBase<UserSchema, ObjectId> {

    @Inject
    UserSchemaMapper userSchemaMapper;

    @Override
    public User getById(String id) {
        UserSchema userSchema = findById(new ObjectId(id));
        return userSchemaMapper.userSchemaToUser(userSchema);
    }

    @Override
    public User getByName(String name) {
        UserSchema userSchema = find("name", name).firstResult();
        return userSchemaMapper.userSchemaToUser(userSchema);
    }

    @Override
    public User getByEmail(String email) {
        UserSchema userSchema = find("email", email).firstResult();
        return userSchemaMapper.userSchemaToUser(userSchema);
    }

    @Override
    public List<User> getAll() {
        List<UserSchema> userSchemas = findAll().list();
        return userSchemas
                .stream()
                .map(userSchema -> userSchemaMapper.userSchemaToUser(userSchema))
                .toList();
    }

    @Override
    public String save(User user) {
        UserSchema userSchema = userSchemaMapper.userToUserSchema(user);
        persist(userSchema);
        return userSchema.getId().toString();
    }

    @Override
    public void merge(User user) {
        UserSchema userSchema = userSchemaMapper.userToUserSchema(user);
        update(userSchema);
    }

    @Override
    public User login(String email, String password) {
        UserSchema userSchema = find("{ email: ?1, password: ?2 }", email, password).firstResult();
        return userSchemaMapper.userSchemaToUser(userSchema);
    }
}
