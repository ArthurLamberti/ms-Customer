package com.arthurlamberti.cdb.customer.create;

import com.arthurlamberti.cdb.UseCase;

public sealed abstract class CreateCustomerUseCase
        extends UseCase<CreateCustomerCommand, CreateCustomerOutput>
        permits DefaultCreateCustomerUseCase {
}
