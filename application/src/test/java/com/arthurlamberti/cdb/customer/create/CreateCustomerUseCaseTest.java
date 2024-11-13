package com.arthurlamberti.cdb.customer.create;

import com.arthurlamberti.cdb.Fixture;
import com.arthurlamberti.cdb.UseCaseTest;
import com.arthurlamberti.cdb.customer.CustomerGateway;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CreateCustomerUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultCreateCustomerUseCase useCase;

    @Mock
    private CustomerGateway customerGateway;

    @Test
    public void givenAValidCommand_whenCallsCreateCustomer_shouldReturnIt() {
        final var expectedCustomer = Fixture.CustomerFixture.validCustomer();
        final var aCommand = CreateCustomerCommand.with(
                expectedCustomer.getName(),
                expectedCustomer.getEmail(),
                expectedCustomer.getDocument()
        );

        when(customerGateway.create(any())).thenAnswer(returnsFirstArg());
        final var actualResult = useCase.execute(aCommand);

        assertNotNull(actualResult);
        verify(customerGateway, times(1)).create(
                argThat(customer ->
                        Objects.nonNull(customer.getId())
                                && Objects.equals(expectedCustomer.getName(), customer.getName())
                                && Objects.equals(expectedCustomer.getDocument(), customer.getDocument())
                                && Objects.equals(expectedCustomer.getEmail(), customer.getEmail())
                )
        );
    }
}