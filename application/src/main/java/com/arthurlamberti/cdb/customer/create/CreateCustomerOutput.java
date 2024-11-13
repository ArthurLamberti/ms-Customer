package com.arthurlamberti.cdb.customer.create;

import com.arthurlamberti.cdb.customer.Customer;
import com.arthurlamberti.cdb.customer.CustomerID;

public record CreateCustomerOutput (String id){
    public static CreateCustomerOutput from (final Customer aCustomer) {
        return new CreateCustomerOutput(aCustomer.getId().getValue());
    }
}
