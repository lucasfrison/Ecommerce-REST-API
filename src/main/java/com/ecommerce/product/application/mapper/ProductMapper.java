package com.ecommerce.product.application.mapper;

import com.ecommerce.product.application.dto.ProductGenericDto;
import com.ecommerce.product.application.dto.ProductPersistRequestDto;
import com.ecommerce.product.domain.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta")
public interface ProductMapper {

    ProductGenericDto productToProductDto(Product product);
    Product productPostRequestDtoToProduct(ProductPersistRequestDto productPersistRequestDto);

}
