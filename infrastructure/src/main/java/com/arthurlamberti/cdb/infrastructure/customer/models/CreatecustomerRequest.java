package com.arthurlamberti.cdb.infrastructure.customer.models;

public record CreatecustomerRequest(
        String name,
        String description,
        String email
) {
}
