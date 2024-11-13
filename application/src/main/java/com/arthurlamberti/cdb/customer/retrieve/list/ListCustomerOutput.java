package com.arthurlamberti.cdb.customer.retrieve.list;

import com.arthurlamberti.cdb.customer.Customer;

public record ListCustomerOutput(
        String id,
        String name,
        String document,
        String email
) {
    public static ListCustomerOutput from(final Customer customer) {
        return new ListCustomerOutput(
                customer.getId().getValue(),
                customer.getName(),
                customer.getDocument(),
                customer.getEmail()
        );
    }
}
