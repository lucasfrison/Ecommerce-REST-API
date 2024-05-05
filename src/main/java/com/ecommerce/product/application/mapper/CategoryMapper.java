package com.ecommerce.product.application.mapper;

import com.ecommerce.product.application.dto.CategoryGenericDto;
import com.ecommerce.product.application.dto.CategoryPersistRequestDto;
import com.ecommerce.product.domain.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta")
public interface CategoryMapper {

    CategoryGenericDto categoryToCategoryDto(Category category);
    Category categoryPostRequestDtoToCategory(CategoryPersistRequestDto categoryPersistRequestDto);

}
