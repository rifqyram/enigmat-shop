package com.enigma.enigmat_shop.service;

import com.enigma.enigmat_shop.dto.CustomerDTO;
import com.enigma.enigmat_shop.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    Customer create(Customer customer);

    Customer get(String id);

    List<Customer> list();

    Page<Customer> listWithPage(Pageable pageable, CustomerDTO customerDTO);

    Customer update(Customer customer);

    String delete(String id);
}

