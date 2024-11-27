package com.arthurlamberti.cdb.customer.retrieve.get;

import com.arthurlamberti.cdb.Fixture;
import com.arthurlamberti.cdb.UseCaseTest;
import com.arthurlamberti.cdb.adapters.feing.CustomerWalletExternal;
import com.arthurlamberti.cdb.customer.CustomerGateway;
import com.arthurlamberti.cdb.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GetCustomerUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultGetCustomerUseCase useCase;

    @Mock
    private CustomerGateway customerGateway;

    @Mock
    private CustomerWalletExternal customerWalletExternal;

    @Override
    protected List<Object> getMocks() {
        return List.of(customerGateway, customerWalletExternal);
    }

    @Test
    public void givenAValidId_whenCallsGetCustomer_shouldReturnIt(){
        final var expectedCustomer = Fixture.CustomerFixture.validCustomer();
        final var expectedBalance = Fixture.positiveNumber();

        when(customerGateway.findById(any())).thenReturn(Optional.of(expectedCustomer));
        when(customerWalletExternal.getBalance(any())).thenReturn(expectedBalance.doubleValue());

        final var actualResult = useCase.execute(expectedCustomer.getId().getValue());

        assertNotNull(actualResult);
        assertEquals(expectedCustomer.getId().getValue(), actualResult.id());
        assertEquals(expectedBalance.doubleValue(), actualResult.balance());
        assertEquals(expectedCustomer.getName(), actualResult.name());
        assertEquals(expectedCustomer.getDocument(), actualResult.document());
        assertEquals(expectedCustomer.getEmail(), actualResult.email());

        verify(customerGateway, times(1)).findById(any());
        verify(customerWalletExternal, times(1)).getBalance(any());
    }

    @Test
    public void givenAnInvalidId_whenCallsGetCustomer_shouldReturnAnException(){
        final var expectedId = Fixture.uuid();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "Customer not found to id %s".formatted(expectedId);

        when(customerGateway.findById(any())).thenReturn(Optional.empty());

        final var actualException = assertThrows(NotFoundException.class, () -> useCase.execute(expectedId));

        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());

        verify(customerGateway, times(1)).findById(any());
        verify(customerWalletExternal, never()).getBalance(any());
    }
}