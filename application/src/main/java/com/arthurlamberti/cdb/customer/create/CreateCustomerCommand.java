package com.arthurlamberti.cdb.customer.create;

public record CreateCustomerCommand (
        String name,
        String email,
        String document
){
    public static CreateCustomerCommand with(
            final String aName,
            final String anEmail,
            final String aDocument
    ) {
        return new CreateCustomerCommand(aName, anEmail, aDocument);
    }
}
