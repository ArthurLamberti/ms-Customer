package com.arthurlamberti.cdb.customer;

import com.arthurlamberti.cdb.AggregateRoot;
import com.arthurlamberti.cdb.exceptions.NotificationException;
import com.arthurlamberti.cdb.validation.ValidationHandler;
import com.arthurlamberti.cdb.validation.handler.Notification;

public class Customer extends AggregateRoot<CustomerID> {

    private String name;
    private String document;
    private String email;

    protected Customer(
            final CustomerID anId,
            final String aName,
            final String aDocument,
            final String anEmail) {
        super(anId);
        this.name = aName;
        this.document = aDocument;
        this.email = anEmail;

        selfValidate();
    }

    public static Customer newCustomer(
            final String aName,
            final String aDocument,
            final String anEmail
    ) {
        final var anId = CustomerID.unique();
        return new Customer(anId,aName,aDocument,anEmail);
    }

    public static Customer with(
            final CustomerID id,
            final String name,
            final String document,
            final String email) {
        return new Customer(
                id,
                name,
                document,
                email
        );
    }

    @Override
    public void validate(ValidationHandler handler) {
        new CustomerValidator(handler, this).validate();
    }

    private void selfValidate(){
        final var notification = Notification.create();
        validate(notification);
        if (notification.hasError()){
            throw new NotificationException("Failed to create a Customer", notification);
        }
    }

    public String getName() {
        return name;
    }

    public String getDocument() {
        return document;
    }

    public String getEmail() {
        return email;
    }
}
