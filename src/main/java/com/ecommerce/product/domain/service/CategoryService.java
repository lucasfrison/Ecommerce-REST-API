package com.ecommerce.product.domain.service;

import com.ecommerce.product.domain.entity.Category;

import java.util.List;

public interface CategoryService {

    Category getById(String id);
    List<Category> getAll();
    String save(Category category);
    void remove(String id);
    void merge(Category category);

}
