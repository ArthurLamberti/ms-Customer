package com.arthurlamberti.cdb.adapters.models;

public record CreateWalletDomain(
        Double balance,
        String customerId
) {
    public static CreateWalletDomain with(
            final Double balance,
            final String customerId
    ) {
        return new CreateWalletDomain(balance, customerId);
    }
}
