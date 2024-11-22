package com.arthurlamberti.cdb.infrastructure.api;

import com.arthurlamberti.cdb.customer.retrieve.get.GetCustomerOutput;
import com.arthurlamberti.cdb.infrastructure.customer.models.CreatecustomerRequest;
import com.arthurlamberti.cdb.infrastructure.customer.models.ListCustomerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "customers")
@Tag(name = "Customers")
public interface CustomerAPI {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Create a new customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was throw"),
            @ApiResponse(responseCode = "500", description = "Internal error server"),
    })
    ResponseEntity<?> createCustomer(@RequestBody CreatecustomerRequest input);

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "List all customers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer list successfully"),
            @ApiResponse(responseCode = "500", description = "Internal error server"),
    })
    List<ListCustomerResponse> listCustomer();

    @GetMapping(
            value = "/{customerId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Get a customers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer get successfully"),
            @ApiResponse(responseCode = "500", description = "Internal error server"),
    })
    GetCustomerOutput getCustomer(@PathVariable String customerId);

}
