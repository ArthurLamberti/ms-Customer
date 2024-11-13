package com.arthurlamberti.cdb.customer.create;

import com.arthurlamberti.cdb.customer.Customer;
import com.arthurlamberti.cdb.customer.CustomerGateway;
import com.arthurlamberti.cdb.exceptions.NotificationException;
import com.arthurlamberti.cdb.validation.handler.Notification;

import static java.util.Objects.requireNonNull;

public non-sealed class DefaultCreateCustomerUseCase extends CreateCustomerUseCase {

    private final CustomerGateway customerGateway;

    public DefaultCreateCustomerUseCase(
            final CustomerGateway customerGateway
    ) {
        this.customerGateway = requireNonNull(customerGateway);
    }

    @Override
    public CreateCustomerOutput execute(CreateCustomerCommand aCommand) {
        final var notification = Notification.create();
        final var aCustomer = notification.validate(
                () -> Customer.newCustomer(
                        aCommand.name(),
                        aCommand.document(),
                        aCommand.email()
                )
        );

        if (notification.hasError()) {
            throw new NotificationException("Could not create Aggregate Customer", notification);
        }

        return CreateCustomerOutput.from(this.customerGateway.create(aCustomer));
    }
}
