package com.enigma.enigmat_shop.specification;

import com.enigma.enigmat_shop.dto.CustomerDTO;
import com.enigma.enigmat_shop.entity.Customer;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CustomerSpecification {
    public CustomerSpecification() {
    }

    public static Specification<Customer> getSpecification(CustomerDTO customerDTO) {
        return new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList();
                if (customerDTO.getSearchByCustomerName() != null) {
                    Predicate customerNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + customerDTO.getSearchByCustomerName().toLowerCase() + "%");
                    predicates.add(customerNamePredicate);
                }

                if (customerDTO.getSearchByCustomerBirthDate() != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String modifiedDateFormat = sdf.format(Date.valueOf(customerDTO.getSearchByCustomerBirthDate()));
                    Predicate customerBirthDatePredicate = criteriaBuilder.equal(criteriaBuilder.function("TO_CHAR", String.class, root.get("birthDate"), criteriaBuilder.literal("yyyy-MM-dd")), modifiedDateFormat);
                    predicates.add(customerBirthDatePredicate);
                }

                Predicate[] arrayPredicates = predicates.toArray(new Predicate[predicates.size()]);
                return criteriaBuilder.and(arrayPredicates);
            }
        };
    }
}