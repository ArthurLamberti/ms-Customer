package com.arthurlamberti.cdb.infrastructure.customer.models;

public record ListCustomerResponse (
        String id,
        String name,
        String document,
        String email
){
}
