package com.ecommerce.product.domain.service;

import com.ecommerce.product.domain.entity.Product;
import com.ecommerce.product.domain.repository.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class DomainProductService implements ProductService {

    @Inject
    ProductRepository productRepository;

    @Override
    public Product getById(String id) {
        return productRepository.getById(id);
    }

    @Override
    public Product getByName(String name) {
        return productRepository.getByName(name);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.getAll();
    }

    @Override
    public List<Product> getByCategory(String categoryId) {
        return productRepository.getByCategory(categoryId);
    }

    @Override
    public String save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void remove(String id) {
        productRepository.remove(id);
    }

    @Override
    public void merge(Product product) {
        productRepository.merge(product);
    }
}
