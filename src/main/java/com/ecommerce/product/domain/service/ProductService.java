package com.ecommerce.product.domain.service;

import com.ecommerce.product.domain.entity.Product;

import java.util.List;

public interface ProductService {

    Product getById(String id);
    Product getByName(String name);
    List<Product> getAll();
    List<Product> getByCategory(String categoryId);
    String save(Product product);
    void remove(String id);
    void merge(Product product);

}
