package com.arthurlamberti.cdb.customer;

import com.arthurlamberti.cdb.Pagination;

public interface CustomerGateway {

    Customer create(final Customer aCustomer);

    Pagination<Customer> findAll();
}
