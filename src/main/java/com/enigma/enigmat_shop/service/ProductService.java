package com.enigma.enigmat_shop.service;

import com.enigma.enigmat_shop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Product create(Product product);

    Product getById(String id);

    List<Product> list();

    Page<Product> listPage(Pageable pageable);

    Product update(Product product);

    String delete(String id);
}
