package com.arthurlamberti.cdb.application.customer.retrieve;

import com.arthurlamberti.cdb.Fixture;
import com.arthurlamberti.cdb.IntegrationTest;
import com.arthurlamberti.cdb.customer.Customer;
import com.arthurlamberti.cdb.customer.retrieve.get.GetCustomerUseCase;
import com.arthurlamberti.cdb.exceptions.NotFoundException;
import com.arthurlamberti.cdb.infrastructure.customer.persistence.CustomerJpaEntity;
import com.arthurlamberti.cdb.infrastructure.customer.persistence.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@IntegrationTest
public class GetCustomerUseCaseIT {

    @Autowired
    private GetCustomerUseCase getCustomerUseCase;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void givenAValidId_whenCallsGetCustomer_shouldReturnIt() {
        final var expectedCustomer = Fixture.CustomerFixture.validCustomer();
        final var expectedId = expectedCustomer.getId().getValue();

        this.customerRepository.saveAndFlush(CustomerJpaEntity.from(expectedCustomer));
        assertEquals(1, this.customerRepository.count());

        final var actualOutput = this.getCustomerUseCase.execute(expectedId);

        assertNotNull(actualOutput);
        assertEquals(expectedCustomer.getId().getValue(), actualOutput.id());
        assertEquals(expectedCustomer.getEmail(), actualOutput.email());
        assertEquals(expectedCustomer.getName(), actualOutput.name());
        assertEquals(expectedCustomer.getDocument(), actualOutput.document());
    }

    @Test
    public void givenAInvalidId_whenCallsGetCustomer_shouldReturnAnException() {
        final var expectedCustomer = Fixture.CustomerFixture.validCustomer();
        final var expectedId = expectedCustomer.getId().getValue();
        final var expectedException = "Customer not found to id %s".formatted(expectedId);

        assertEquals(0, this.customerRepository.count());
        final var actualOutput = assertThrows(NotFoundException.class, () -> this.getCustomerUseCase.execute(expectedId));
        assertNotNull(actualOutput);
        assertEquals(expectedException, actualOutput.getFirstError().get().message());
    }
}
