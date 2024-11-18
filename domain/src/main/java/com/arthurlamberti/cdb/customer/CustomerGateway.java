package com.arthurlamberti.cdb.customer;

import java.util.List;

public interface CustomerGateway {

    Customer create(final Customer aCustomer);

    List<Customer> findAll();

    boolean existsByDocument(String document);
    boolean existsByEmail(String email);
}
