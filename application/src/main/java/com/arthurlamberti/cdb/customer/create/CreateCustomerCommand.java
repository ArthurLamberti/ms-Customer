package com.arthurlamberti.cdb.customer.create;

public record CreateCustomerCommand (
        String name,
        String document,
        String email,
        Double balance
){
    public static CreateCustomerCommand with(
            final String aName,
            final String aDocument,
            final String anEmail,
            final Double aBalance
    ) {
        return new CreateCustomerCommand(aName, aDocument, anEmail, aBalance);
    }
}
