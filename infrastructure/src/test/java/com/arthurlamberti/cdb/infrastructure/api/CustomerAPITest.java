package com.arthurlamberti.cdb.infrastructure.api;

import com.arthurlamberti.cdb.ControllerTest;
import com.arthurlamberti.cdb.Fixture;
import com.arthurlamberti.cdb.application.customer.create.CreateCustomerUseCaseIT;
import com.arthurlamberti.cdb.customer.create.CreateCustomerOutput;
import com.arthurlamberti.cdb.customer.create.CreateCustomerUseCase;
import com.arthurlamberti.cdb.customer.retrieve.get.GetCustomerOutput;
import com.arthurlamberti.cdb.customer.retrieve.get.GetCustomerUseCase;
import com.arthurlamberti.cdb.customer.retrieve.list.ListCustomerUsecase;
import com.arthurlamberti.cdb.infrastructure.customer.models.CreatecustomerRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyChar;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ControllerTest
public class CustomerAPITest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreateCustomerUseCase createCustomerUseCase;

    @MockBean
    private ListCustomerUsecase listCustomerUsecase;

    @MockBean
    private GetCustomerUseCase getCustomerUseCase;

    @Test
    public void givenAValidCommand_whenCalsCreateCustomer_shouldReturnCustomerId() throws Exception {
        final var expectedName = Fixture.name();
        final var expectedDocument = Fixture.document();
        final var expectedEmail = Fixture.email();
        final var expectedId = Fixture.uuid();

        final var anInput = new CreatecustomerRequest(expectedName, expectedDocument, expectedEmail, Fixture.positiveNumber().doubleValue());

        when(createCustomerUseCase.execute(any())).thenReturn(CreateCustomerOutput.from(expectedId));

        final var aRequest = post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(anInput));
        final var response = this.mockMvc.perform(aRequest).andDo(print());

        response.andExpect(status().isCreated())
                .andExpect(header().string("Location", "/customers/"+expectedId))
                .andExpect(jsonPath("$.id", equalTo(expectedId)));
    }

    @Test
    public void givenAValidId_whenCallsGetCustomerById_shouldReturnCustomer() throws Exception {
        final var expectedCustomer = Fixture.CustomerFixture.validCustomer();
        final var expectedBalance = Fixture.positiveNumber().doubleValue();
        when(getCustomerUseCase.execute(any())).thenReturn(GetCustomerOutput.from(expectedCustomer, expectedBalance));

        final var aRequest = get("/customers/"+expectedCustomer.getId());
        final var response = this.mockMvc.perform(aRequest).andDo(print());

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(expectedCustomer.getId().getValue())))
                .andExpect(jsonPath("$.name", equalTo(expectedCustomer.getName())))
                .andExpect(jsonPath("$.document", equalTo(expectedCustomer.getDocument())))
                .andExpect(jsonPath("$.email", equalTo(expectedCustomer.getEmail())))
                .andExpect(jsonPath("$.balance", equalTo(expectedBalance)));
    }
}