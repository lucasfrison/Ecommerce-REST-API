package com.ecommerce.product.application.mapper;

import com.ecommerce.product.application.dto.ProductGenericDto;
import com.ecommerce.product.application.dto.ProductPersistRequestDto;
import com.ecommerce.product.domain.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "jakarta")
public interface ProductMapper {

    ProductGenericDto productToProductDto(Product product);
    Product productPostRequestDtoToProduct(ProductPersistRequestDto productPersistRequestDto);

    default List<String> imagesToImages(List<String> images) {
        return images;
    }

}
