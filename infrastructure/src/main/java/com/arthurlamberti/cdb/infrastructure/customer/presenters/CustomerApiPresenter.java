package com.arthurlamberti.cdb.infrastructure.customer.presenters;

import com.arthurlamberti.cdb.customer.retrieve.list.ListCustomerOutput;
import com.arthurlamberti.cdb.infrastructure.customer.models.ListCustomerResponse;

public interface CustomerApiPresenter {

    static ListCustomerResponse present(final ListCustomerOutput output){
        return new ListCustomerResponse(
                output.id(),
                output.name(),
                output.document(),
                output.email()
        );
    }

}
