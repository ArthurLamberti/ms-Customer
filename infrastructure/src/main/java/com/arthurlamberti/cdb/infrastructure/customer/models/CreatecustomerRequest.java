package com.arthurlamberti.cdb.infrastructure.customer.models;

public record CreatecustomerRequest(
        String name,
        String document,
        String email,
        Double balance
) {
}
