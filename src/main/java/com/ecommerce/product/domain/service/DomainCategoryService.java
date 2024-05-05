package com.ecommerce.product.domain.service;

import com.ecommerce.product.domain.entity.Category;
import com.ecommerce.product.domain.repository.CategoryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class DomainCategoryService implements CategoryService {

    @Inject
    CategoryRepository categoryRepository;

    @Override
    public Category getById(String id) {
        return categoryRepository.getById(id);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.getAll();
    }

    @Override
    public String save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void remove(String id) {
        categoryRepository.remove(id);
    }

    @Override
    public void merge(Category category) {
        categoryRepository.merge(category);
    }
}
