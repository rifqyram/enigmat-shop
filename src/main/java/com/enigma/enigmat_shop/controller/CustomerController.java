package com.enigma.enigmat_shop.controller;

import com.enigma.enigmat_shop.dto.CustomerDTO;
import com.enigma.enigmat_shop.entity.Customer;
import com.enigma.enigmat_shop.response.PageResponse;
import com.enigma.enigmat_shop.service.CustomerService;
import com.enigma.enigmat_shop.util.WebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/customers"})
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    public CustomerController() {
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return this.customerService.create(customer);
    }

    @GetMapping({"/{customerId}"})
    public ResponseEntity<WebResponse<Customer>> getById(@PathVariable("customerId") String id) {
        Customer customer = this.customerService.get(id);
        WebResponse<Customer> response = new WebResponse<>(String.format("Customer with id %s found", id), customer);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<WebResponse<PageResponse<Customer>>> listWithPage(
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "birthdate", required = false) String birthDate) {
        Pageable pageable = PageRequest.of(page, size);
        CustomerDTO customerDTO = new CustomerDTO(name, birthDate);

        Page<Customer> customers = this.customerService.listWithPage(pageable, customerDTO);

        PageResponse<Customer> pageResponse = new PageResponse<>(
                customers.getContent(),
                customers.getTotalElements(),
                customers.getTotalPages(),
                page,
                size
        );

        WebResponse<PageResponse<Customer>> response = new WebResponse<>("Successfully get data customer", pageResponse);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping
    public Customer updateCustomerById(@RequestBody Customer customer) {
        return this.customerService.update(customer);
    }

    @DeleteMapping({"/{customerId}"})
    public String deleteCustomerById(@PathVariable("customerId") String id) {
        return this.customerService.delete(id);
    }
}
