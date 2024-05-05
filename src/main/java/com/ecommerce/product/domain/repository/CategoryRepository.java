package com.ecommerce.product.domain.repository;

import com.ecommerce.product.domain.entity.Category;

import java.util.List;

public interface CategoryRepository {

    Category getById(String id);
    List<Category> getAll();
    String save(Category category);
    void remove(String id);
    void merge(Category category);

}
