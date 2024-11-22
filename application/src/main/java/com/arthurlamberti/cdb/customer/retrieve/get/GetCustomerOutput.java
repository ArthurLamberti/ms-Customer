package com.arthurlamberti.cdb.customer.retrieve.get;

import com.arthurlamberti.cdb.customer.Customer;
import com.arthurlamberti.cdb.customer.retrieve.list.ListCustomerOutput;

public record GetCustomerOutput (

        String id,
        String name,
        String document,
        String email,
        Double balance
){

    public static GetCustomerOutput from(final Customer customer, final Double balance) {
        return new GetCustomerOutput(
                customer.getId().getValue(),
                customer.getName(),
                customer.getDocument(),
                customer.getEmail(),
                balance
        );
    }
}
