package com.ecommerce.product.infrastructure.mapper;

import com.ecommerce.product.domain.entity.Category;
import com.ecommerce.product.infrastructure.schema.CategorySchema;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta")
public interface CategorySchemaMapper {

    Category categorySchemaToCategory(CategorySchema categorySchema);
    CategorySchema categoryToCategorySchema(Category category);

    default String fromObjectId(ObjectId id) {
        return id == null ? "" : id.toString();
    }

    default ObjectId toObjectId(String id) {
        return id == null ? null : new ObjectId(id);
    }

}
