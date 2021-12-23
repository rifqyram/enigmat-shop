package com.enigma.enigmat_shop.service.impl;

import com.enigma.enigmat_shop.entity.Product;
import com.enigma.enigmat_shop.exception.NotFoundException;
import com.enigma.enigmat_shop.repository.ProductRepository;
import com.enigma.enigmat_shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ProductServiceImpl() {
    }

    public Product create(Product product) {
        return (Product)this.productRepository.save(product);
    }

    public Product getById(String id) {
        Optional<Product> product = this.productRepository.findById(id);
        if (product.isPresent()) {
            return (Product)product.get();
        } else {
            throw new NotFoundException(String.format("Product dengan id %s tidak ditemukan", id));
        }
    }

    public List<Product> list() {
        return this.productRepository.findAll();
    }

    public Page<Product> listPage(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    public Product update(Product product) {
        this.getById(product.getId());
        return this.productRepository.save(product);
    }

    public String delete(String id) {
        Product product = this.getById(id);
        this.productRepository.delete(product);
        return String.format("Product dengan id %s berhasil terhapus", id);
    }
}
