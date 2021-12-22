package com.enigma.enigmat_shop.controller;

import com.enigma.enigmat_shop.entity.Product;
import com.enigma.enigmat_shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/products"})
public class ProductController {
    @Autowired
    private ProductService productService;

    public ProductController() {
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return this.productService.create(product);
    }

    @GetMapping({"/{productId}"})
    public Product getProductById(@PathVariable("productId") String id) {
        return this.productService.getById(id);
    }

    @GetMapping
    public Page<Product> getListWithPage(
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "sortBy", defaultValue = "productName") String sortBy,
            @RequestParam(name = "direction", defaultValue = "ASC") String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return this.productService.listPage(pageable);
    }

    @PutMapping
    public Product updateProductById(@RequestBody Product product) {
        return this.productService.update(product);
    }

    @DeleteMapping({"/{productId}"})
    public String deleteProductById(@PathVariable("productId") String id) {
        return this.productService.delete(id);
    }
}
