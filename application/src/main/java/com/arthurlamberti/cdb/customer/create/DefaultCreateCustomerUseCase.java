package com.arthurlamberti.cdb.customer.create;

import com.arthurlamberti.cdb.customer.Customer;
import com.arthurlamberti.cdb.customer.CustomerGateway;
import com.arthurlamberti.cdb.exceptions.NotificationException;
import com.arthurlamberti.cdb.validation.Error;
import com.arthurlamberti.cdb.validation.handler.Notification;

import static java.util.Objects.requireNonNull;

public class DefaultCreateCustomerUseCase extends CreateCustomerUseCase {

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
        if (this.customerGateway.existsByDocument(aCommand.document())) {
            notification.append(new Error("Customer already exists with document " + aCustomer.getDocument()));
        }
        if (this.customerGateway.existsByEmail(aCommand.email())) {
            notification.append(new Error("Customer already exists with email " + aCustomer.getEmail()));
        }

        if (notification.hasError()) {
            throw new NotificationException("Could not create Aggregate Customer", notification);
        }

        return CreateCustomerOutput.from(this.customerGateway.create(aCustomer));
    }
}
