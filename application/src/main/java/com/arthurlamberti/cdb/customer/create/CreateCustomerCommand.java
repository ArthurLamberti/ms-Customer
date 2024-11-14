package com.arthurlamberti.cdb.customer.create;

public record CreateCustomerCommand (
        String name,
        String document,
        String email
){
    public static CreateCustomerCommand with(
            final String aName,
            final String aDocument,
            final String anEmail
    ) {
        return new CreateCustomerCommand(aName, aDocument, anEmail);
    }
}
