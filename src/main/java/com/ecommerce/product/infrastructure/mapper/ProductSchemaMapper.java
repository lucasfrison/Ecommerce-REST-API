package com.ecommerce.product.infrastructure.mapper;

import com.ecommerce.product.domain.entity.Product;
import com.ecommerce.product.infrastructure.schema.ProductSchema;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "jakarta")
public interface ProductSchemaMapper {

    Product productSchemaToProduct(ProductSchema productSchema);
    ProductSchema productToProductSchema(Product product);

    default String fromObjectId(ObjectId id) {
        return id == null ? "" : id.toString();
    }

    default ObjectId toObjectId(String id) {
        return id == null ? null : new ObjectId(id);
    }

    default List<String> imagesToImages(List<String> images) {
        return images;
    }

}
