package com.arthurlamberti.cdb.customer.retrieve.get;

import com.arthurlamberti.cdb.adapters.feing.CustomerWalletExternal;
import com.arthurlamberti.cdb.customer.CustomerGateway;
import com.arthurlamberti.cdb.exceptions.NotFoundException;
import com.arthurlamberti.cdb.exceptions.NotificationException;
import com.arthurlamberti.cdb.validation.Error;

public class DefaultGetCustomerUseCase extends GetCustomerUseCase{

    private final CustomerGateway customerGateway;
    private final CustomerWalletExternal customerWalletExternal;

    public DefaultGetCustomerUseCase(
            final CustomerGateway customerGateway,
            final CustomerWalletExternal customerWalletExternal) {
        this.customerGateway = customerGateway;
        this.customerWalletExternal = customerWalletExternal;
    }

    @Override
    public GetCustomerOutput execute(String customerId) {
        final var customer = this.customerGateway.findById(customerId);
        if (customer.isEmpty()) {
            throw NotFoundException.with(new Error("Customer not found to id %s".formatted(customer)));
        }
        final var balance = this.customerWalletExternal.getBalance(customerId);

        return GetCustomerOutput.from(customer.get(),balance);
    }
}
