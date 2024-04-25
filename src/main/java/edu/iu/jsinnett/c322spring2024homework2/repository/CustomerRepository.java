package edu.iu.jsinnett.c322spring2024homework2.repository;

import edu.iu.jsinnett.c322spring2024homework2.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Customer findByUsername(String username);
}

