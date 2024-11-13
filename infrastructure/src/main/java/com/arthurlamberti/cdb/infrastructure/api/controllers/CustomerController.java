package com.arthurlamberti.cdb.infrastructure.api.controllers;

import com.arthurlamberti.cdb.infrastructure.api.CustomerAPI;
import com.arthurlamberti.cdb.infrastructure.customer.models.CreatecustomerRequest;
import com.arthurlamberti.cdb.customer.create.CreateCustomerCommand;
import com.arthurlamberti.cdb.customer.create.CreateCustomerUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class CustomerController implements CustomerAPI {

    private final CreateCustomerUseCase createCustomerUseCase;

    public CustomerController(
            final CreateCustomerUseCase createCustomerUseCase
    ) {
        this.createCustomerUseCase = createCustomerUseCase;
    }

    @Override
    public ResponseEntity<?> createCustomer(CreatecustomerRequest input) {
        final var aCommand = CreateCustomerCommand.with(
                input.name(),
                input.email(),
                input.description()
        );
       final var output = this.createCustomerUseCase.execute(aCommand);

       return ResponseEntity.created(URI.create("/customers/"+output.id())).body(output);
    }

}
