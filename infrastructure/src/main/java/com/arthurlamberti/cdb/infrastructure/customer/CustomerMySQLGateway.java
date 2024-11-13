package com.arthurlamberti.cdb.infrastructure.customer;

import com.arthurlamberti.cdb.Pagination;
import com.arthurlamberti.cdb.customer.Customer;
import com.arthurlamberti.cdb.customer.CustomerGateway;
import org.springframework.stereotype.Component;

@Component
public class CustomerMySQLGateway implements CustomerGateway {
    @Override
    public Customer create(Customer aCustomer) {
        return null;
    }

    @Override
    public Pagination<Customer> findAll() {
        return null;
    }
}
