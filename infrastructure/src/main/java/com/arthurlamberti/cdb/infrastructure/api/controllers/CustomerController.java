package com.arthurlamberti.cdb.infrastructure.api.controllers;

import com.arthurlamberti.cdb.customer.retrieve.list.ListCustomerUsecase;
import com.arthurlamberti.cdb.infrastructure.api.CustomerAPI;
import com.arthurlamberti.cdb.infrastructure.customer.models.CreatecustomerRequest;
import com.arthurlamberti.cdb.customer.create.CreateCustomerCommand;
import com.arthurlamberti.cdb.customer.create.CreateCustomerUseCase;
import com.arthurlamberti.cdb.infrastructure.customer.models.ListCustomerResponse;
import com.arthurlamberti.cdb.infrastructure.customer.presenters.CustomerApiPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class CustomerController implements CustomerAPI {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final ListCustomerUsecase listCustomerUsecase;

    public CustomerController(
            final CreateCustomerUseCase createCustomerUseCase,
            final ListCustomerUsecase listCustomerUsecase
    ) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.listCustomerUsecase = listCustomerUsecase;
    }

    @Override
    public ResponseEntity<?> createCustomer(CreatecustomerRequest input) {
        final var aCommand = CreateCustomerCommand.with(
                input.name(),
                input.email(),
                input.document()
        );
       final var output = this.createCustomerUseCase.execute(aCommand);

       return ResponseEntity.created(URI.create("/customers/"+output.id())).body(output);
    }

    @Override
    public List<ListCustomerResponse> listCustomer() {
        return this.listCustomerUsecase.execute()
                .stream()
                .map(CustomerApiPresenter::present)
                .toList();
    }

}
