package com.arthurlamberti.cdb.customer.retrieve.list;

import com.arthurlamberti.cdb.Fixture;
import com.arthurlamberti.cdb.UseCaseTest;
import com.arthurlamberti.cdb.customer.Customer;
import com.arthurlamberti.cdb.customer.CustomerGateway;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class ListCustomerUsecaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultListCustomerUsecase listCustomerUsecase;

    @Mock
    private CustomerGateway customerGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(customerGateway);
    }

    @Test
    public void givenAValidCommand_whenCallsListCustomers_shouldReturnIt() {
        final var listCustomers = List.of(
                Fixture.CustomerFixture.validCustomer(),
                Fixture.CustomerFixture.validCustomer(),
                Fixture.CustomerFixture.validCustomer()
        );

        final var expectedItems = listCustomers.stream()
                .map(ListCustomerOutput::from)
                .toList();

        when(customerGateway.findAll()).thenReturn(listCustomers);

        final var output = listCustomerUsecase.execute();
        assertNotNull(output);
        assertTrue(expectedItems.containsAll(output));
        verify(customerGateway, times(1)).findAll();
    }

    @Test
    public void givenAnEmptyList_whenCallsListCustomers_shouldReturnEmpty() {
        final var listWallets = List.<Customer>of();

        when(customerGateway.findAll()).thenReturn(listWallets);

        final var actualOutput = listCustomerUsecase.execute();
        assertNotNull(actualOutput);
        assertTrue(actualOutput.isEmpty());
        verify(customerGateway, times(1)).findAll();
    }

    @Test
    public void givenGatewayError_whenCallsListWallet_shouldReturnAnError() {
        final var expectedErrorMessage = "Gateway error";
        doThrow(new IllegalStateException(expectedErrorMessage)).when(customerGateway).findAll();

        final var actualException = assertThrows(IllegalStateException.class, () -> listCustomerUsecase.execute());
        assertEquals(expectedErrorMessage, actualException.getMessage());
        verify(customerGateway, times(1)).findAll();
    }
}