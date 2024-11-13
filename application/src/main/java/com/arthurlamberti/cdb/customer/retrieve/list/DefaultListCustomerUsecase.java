package com.arthurlamberti.cdb.customer.retrieve.list;

import com.arthurlamberti.cdb.Pagination;
import com.arthurlamberti.cdb.customer.CustomerGateway;

import static java.util.Objects.requireNonNull;

public non-sealed class DefaultListCustomerUsecase extends ListCustomerUsecase {

    private final CustomerGateway customerGateway;

    public DefaultListCustomerUsecase(final CustomerGateway customerGateway){
        this.customerGateway = requireNonNull(customerGateway);
    }

    @Override
    public Pagination<ListCustomerOutput> execute() {
        return this.customerGateway.findAll().map(ListCustomerOutput::from);
    }
}
