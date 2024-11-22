package com.arthurlamberti.cdb.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerGateway {

    Customer create(final Customer aCustomer);

    List<Customer> findAll();

    boolean existsByDocument(String document);
    boolean existsByEmail(String email);
    Optional<Customer> findById(String customerId);
}
