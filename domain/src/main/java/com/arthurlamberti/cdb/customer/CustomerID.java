package com.arthurlamberti.cdb.customer;

import com.arthurlamberti.cdb.AggregateRoot;
import com.arthurlamberti.cdb.Identifier;
import com.arthurlamberti.cdb.utils.IdUtils;
import com.arthurlamberti.cdb.validation.ValidationHandler;

import static java.util.Objects.requireNonNull;

public class CustomerID extends Identifier {

    private final String uuid;

    public CustomerID(final String uuid) {
        requireNonNull(uuid);
        this.uuid = uuid;
    }

    public static CustomerID unique(){
        return CustomerID.from(IdUtils.uuid());
    }

    public static CustomerID from(final String anId) {
        return new CustomerID(anId.toLowerCase());
    }

    @Override
    public String getValue() {
        return this.uuid;
    }
}
