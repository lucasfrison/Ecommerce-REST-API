package com.ecommerce.product.infrastructure.repository;

import com.ecommerce.product.domain.entity.Category;
import com.ecommerce.product.domain.repository.CategoryRepository;
import com.ecommerce.product.infrastructure.mapper.CategorySchemaMapper;
import com.ecommerce.product.infrastructure.schema.CategorySchema;
import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.List;

@ApplicationScoped
public class MongoDBCategoryRepository implements CategoryRepository, PanacheMongoRepositoryBase<CategorySchema, ObjectId> {

    @Inject
    CategorySchemaMapper categorySchemaMapper;

    @Override
    public Category getById(String id) {
        CategorySchema categorySchema = findById(new ObjectId(id));
        return categorySchemaMapper.categorySchemaToCategory(categorySchema);
    }

    @Override
    public List<Category> getAll() {
        List<CategorySchema> categorySchemas = findAll().list();
        return categorySchemas
                    .stream()
                    .map(categorySchema -> categorySchemaMapper.categorySchemaToCategory(categorySchema))
                    .toList();
    }

    @Override
    public String save(Category category) {
        CategorySchema categorySchema = categorySchemaMapper.categoryToCategorySchema(category);
        persist(categorySchema);
        return categorySchema.getId().toString();
    }

    @Override
    public void remove(String id) {
        deleteById(new ObjectId(id));
    }

    @Override
    public void merge(Category category) {
        CategorySchema categorySchema = categorySchemaMapper.categoryToCategorySchema(category);
        update(categorySchema);
    }
}
