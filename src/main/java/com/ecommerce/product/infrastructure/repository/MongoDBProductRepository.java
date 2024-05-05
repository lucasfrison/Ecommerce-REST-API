package com.ecommerce.product.infrastructure.repository;

import com.ecommerce.product.domain.entity.Product;
import com.ecommerce.product.domain.repository.ProductRepository;
import com.ecommerce.product.infrastructure.mapper.ProductSchemaMapper;
import com.ecommerce.product.infrastructure.schema.ProductSchema;
import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.List;

@ApplicationScoped
public class MongoDBProductRepository implements ProductRepository, PanacheMongoRepositoryBase<ProductSchema, ObjectId> {

    @Inject
    ProductSchemaMapper productSchemaMapper;

    @Override
    public Product getById(String id) {
        ProductSchema productSchema = findById(new ObjectId(id));
        return productSchemaMapper.productSchemaToProduct(productSchema);
    }

    @Override
    public Product getByName(String name) {
        ProductSchema productSchema = find("name", name).firstResult();
        return productSchemaMapper.productSchemaToProduct(productSchema);
    }

    @Override
    public List<Product> getAll() {
        List<ProductSchema> productSchemas = findAll().list();
        return productSchemas
                .stream()
                .map(productSchema -> productSchemaMapper.productSchemaToProduct(productSchema))
                .toList();
    }

    @Override
    public List<Product> getByCategory(String categoryId) {
        List<ProductSchema> productSchemas = find("categoryId", new ObjectId(categoryId)).list();
        return productSchemas
                .stream()
                .map(productSchema -> productSchemaMapper.productSchemaToProduct(productSchema))
                .toList();
    }

    @Override
    public String save(Product product) {
        ProductSchema productSchema = productSchemaMapper.productToProductSchema(product);
        persist(productSchema);
        return productSchema.getId().toString();
    }

    @Override
    public void remove(String id) {
        deleteById(new ObjectId(id));
    }

    @Override
    public void merge(Product product) {
        ProductSchema productSchema = productSchemaMapper.productToProductSchema(product);
        update(productSchema);
    }

}
