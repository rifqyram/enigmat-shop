package com.enigma.enigmat_shop.repository;

import com.enigma.enigmat_shop.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;


@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, String> {
    Page<Customer> findAll(Specification<Customer> specification, Pageable pageable);

    @Query(value = "SELECT c FROM Customer c WHERE c.isDeleted = false AND c.id = ?1")
    Optional<Customer> getActiveCustomer(String id);

}
