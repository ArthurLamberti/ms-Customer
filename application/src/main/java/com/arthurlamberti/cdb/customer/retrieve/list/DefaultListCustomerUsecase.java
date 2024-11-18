package com.arthurlamberti.cdb.customer.retrieve.list;

import com.arthurlamberti.cdb.customer.CustomerGateway;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class DefaultListCustomerUsecase extends ListCustomerUsecase {

    private final CustomerGateway customerGateway;

    public DefaultListCustomerUsecase(final CustomerGateway customerGateway){
        this.customerGateway = requireNonNull(customerGateway);
    }

    @Override
    public List<ListCustomerOutput> execute() {
        return this.customerGateway.findAll()
                .stream()
                .map(ListCustomerOutput::from)
                .toList();
    }
}
