package com.test.compulynx.repository;

import com.test.compulynx.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query("FROM Customer")
    Optional<List<Customer>> findAllCustomers();

    @Query("FROM Customer c where c.customerId =:customerId")
    Optional<Customer> findCustomerByCustomerId(String customerId);

}
