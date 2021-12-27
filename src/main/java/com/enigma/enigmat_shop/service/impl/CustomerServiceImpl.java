package com.enigma.enigmat_shop.service.impl;

import com.enigma.enigmat_shop.dto.CustomerDTO;
import com.enigma.enigmat_shop.entity.Customer;
import com.enigma.enigmat_shop.exception.NotFoundException;
import com.enigma.enigmat_shop.repository.CustomerRepository;
import com.enigma.enigmat_shop.service.CustomerService;
import com.enigma.enigmat_shop.specification.CustomerSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public CustomerServiceImpl() {
    }

    public Customer create(Customer customer) {
        return (Customer) this.customerRepository.save(customer);
    }

    public Customer get(String id) {
        return this.findByIdOrThrowNotFound(id);
    }

    @Override
    public Customer getActiveCustomer(String id) {
        return customerRepository.getActiveCustomer(id).orElseThrow(() -> new NotFoundException("Customer Not found"));
    }

    public List<Customer> list() {
        return this.customerRepository.findAll();
    }

    public Page<Customer> listWithPage(Pageable pageable, CustomerDTO customerDTO) {
        Specification<Customer> specification = CustomerSpecification.getSpecification(customerDTO);
        return customerRepository.findAll(specification, pageable);
    }

    public Customer update(Customer customer) {
        findByIdOrThrowNotFound(customer.getId());
        return customerRepository.save(customer);
    }

    public String delete(String id) {
        Customer customer = this.findByIdOrThrowNotFound(id);
        if (customer.getDeleted()) {
            throw new NotFoundException("Customer not found");
        }
        customer.setDeleted(true);

        customerRepository.save(customer);
        return "Customer berhasil terhapus";
    }

    private Customer findByIdOrThrowNotFound(String id) {
        Optional<Customer> customer = this.customerRepository.findById(id);
        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new NotFoundException("Customer tidak ditemukan");
        }
    }
}
