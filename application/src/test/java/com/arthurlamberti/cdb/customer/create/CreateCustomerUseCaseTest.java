package com.arthurlamberti.cdb.customer.create;

import com.arthurlamberti.cdb.Fixture;
import com.arthurlamberti.cdb.UseCaseTest;
import com.arthurlamberti.cdb.adapters.feing.CustomerWalletExternal;
import com.arthurlamberti.cdb.customer.CustomerGateway;
import com.arthurlamberti.cdb.exceptions.NotificationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
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

    @Mock
    private CustomerWalletExternal customerWalletExternal;

    @Override
    protected List<Object> getMocks() {
        return List.of(customerGateway, customerWalletExternal);
    }

    @Test
    public void givenAValidCommand_whenCallsCreateCustomer_shouldReturnIt() {
        final var expectedCustomer = Fixture.CustomerFixture.validCustomer();
        final var aCommand = CreateCustomerCommand.with(
                expectedCustomer.getName(),
                expectedCustomer.getDocument(),
                expectedCustomer.getEmail(),
                Fixture.positiveNumber().doubleValue()
        );

        when(customerGateway.create(any())).thenAnswer(returnsFirstArg());
        when(customerGateway.existsByDocument(any())).thenReturn(false);
        when(customerGateway.existsByEmail(any())).thenReturn(false);
        when(customerWalletExternal.createWallet(any())).thenReturn(Fixture.uuid());
        final var actualResult = useCase.execute(aCommand);

        assertNotNull(actualResult);
        verify(customerGateway).create(
                argThat(customer ->
                        Objects.nonNull(customer.getId())
                                && Objects.equals(expectedCustomer.getName(), customer.getName())
                                && Objects.equals(expectedCustomer.getDocument(), customer.getDocument())
                                && Objects.equals(expectedCustomer.getEmail(), customer.getEmail())
                )
        );
        verify(customerGateway,times(1)).existsByDocument(any());
        verify(customerGateway,times(1)).existsByEmail(any());
        verify(customerWalletExternal, times(1)).createWallet(any());
    }

    @Test
    public void givenAnInvalidNullName_whenCallsCreateCustomer_shouldReturnAnException() {
        final var expectedCustomer = Fixture.CustomerFixture.validCustomer();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";

        final var aCommand = CreateCustomerCommand.with(
                null,
                expectedCustomer.getDocument(),
                expectedCustomer.getEmail(),
                Fixture.positiveNumber().doubleValue()
        );

        when(customerGateway.existsByDocument(any())).thenReturn(false);
        when(customerGateway.existsByEmail(any())).thenReturn(false);

        final var actualException = assertThrows(NotificationException.class, () -> useCase.execute(aCommand));
        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());

        verify(customerGateway, never()).create(any());
        verify(customerWalletExternal, never()).createWallet(any());
    }

    @Test
    public void givenAnInvalidEmptyName_whenCallsCreateCustomer_shouldReturnAnException() {
        final var expectedCustomer = Fixture.CustomerFixture.validCustomer();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be empty";

        final var aCommand = CreateCustomerCommand.with(
                " ",
                expectedCustomer.getDocument(),
                expectedCustomer.getEmail(),
                Fixture.positiveNumber().doubleValue()
        );

        when(customerGateway.existsByDocument(any())).thenReturn(false);
        when(customerGateway.existsByEmail(any())).thenReturn(false);

        final var actualException = assertThrows(NotificationException.class, () -> useCase.execute(aCommand));
        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());
    }

    @Test
    public void givenAnInvalidNullDocument_whenCallsCreateCustomer_shouldReturnAnException() {
        final var expectedCustomer = Fixture.CustomerFixture.validCustomer();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'document' should not be null";

        final var aCommand = CreateCustomerCommand.with(
                expectedCustomer.getName(),
                null,
                expectedCustomer.getEmail(),
                Fixture.positiveNumber().doubleValue()
        );

        when(customerGateway.existsByDocument(any())).thenReturn(false);
        when(customerGateway.existsByEmail(any())).thenReturn(false);

        final var actualException = assertThrows(NotificationException.class, () -> useCase.execute(aCommand));
        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());

        verify(customerGateway, never()).create(any());
        verify(customerWalletExternal, never()).createWallet(any());
    }

    @Test
    public void givenAnInvalidEmptyDocument_whenCallsCreateCustomer_shouldReturnAnException() {
        final var expectedCustomer = Fixture.CustomerFixture.validCustomer();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'document' should not be empty";

        final var aCommand = CreateCustomerCommand.with(
                expectedCustomer.getName(),
                " ",
                expectedCustomer.getEmail(),
                Fixture.positiveNumber().doubleValue()
        );

        when(customerGateway.existsByDocument(any())).thenReturn(false);
        when(customerGateway.existsByEmail(any())).thenReturn(false);

        final var actualException = assertThrows(NotificationException.class, () -> useCase.execute(aCommand));
        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());

        verify(customerGateway, never()).create(any());
        verify(customerWalletExternal, never()).createWallet(any());
    }

    @Test
    public void givenAnInvalidNullEmail_whenCallsCreateCustomer_shouldReturnAnException() {
        final var expectedCustomer = Fixture.CustomerFixture.validCustomer();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'email' should not be null";

        final var aCommand = CreateCustomerCommand.with(
                expectedCustomer.getName(),
                expectedCustomer.getDocument(),
                null,
                Fixture.positiveNumber().doubleValue()
        );

        when(customerGateway.existsByDocument(any())).thenReturn(false);
        when(customerGateway.existsByEmail(any())).thenReturn(false);

        final var actualException = assertThrows(NotificationException.class, () -> useCase.execute(aCommand));
        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());

        verify(customerGateway, never()).create(any());
        verify(customerWalletExternal, never()).createWallet(any());
    }

    @Test
    public void givenAnInvalidEmptyEmail_whenCallsCreateCustomer_shouldReturnAnException() {
        final var expectedCustomer = Fixture.CustomerFixture.validCustomer();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'email' should not be empty";

        final var aCommand = CreateCustomerCommand.with(
                expectedCustomer.getName(),
                expectedCustomer.getDocument(),
                " ",
                Fixture.positiveNumber().doubleValue()
        );

        when(customerGateway.existsByDocument(any())).thenReturn(false);
        when(customerGateway.existsByEmail(any())).thenReturn(false);

        final var actualException = assertThrows(NotificationException.class, () -> useCase.execute(aCommand));
        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());

        verify(customerGateway, never()).create(any());
        verify(customerWalletExternal, never()).createWallet(any());
    }

    @Test
    public void givenAnExistingDocument_whenCallsCreateCustomer_shouldReturnAnException() {
        final var expectedCustomer = Fixture.CustomerFixture.validCustomer();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "Customer already exists with document '%s'".formatted(expectedCustomer.getDocument());

        final var aCommand = CreateCustomerCommand.with(
                expectedCustomer.getName(),
                expectedCustomer.getDocument(),
                expectedCustomer.getEmail(),
                Fixture.positiveNumber().doubleValue()
        );

        when(customerGateway.existsByDocument(any())).thenReturn(true);

        final var actualException = assertThrows(NotificationException.class, () -> useCase.execute(aCommand));
        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());

        verify(customerGateway, never()).create(any());
        verify(customerWalletExternal, never()).createWallet(any());
    }

    @Test
    public void givenAnExistingEmail_whenCallsCreateCustomer_shouldReturnAnException() {
        final var expectedCustomer = Fixture.CustomerFixture.validCustomer();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "Customer already exists with email '%s'".formatted(expectedCustomer.getEmail());

        final var aCommand = CreateCustomerCommand.with(
                expectedCustomer.getName(),
                expectedCustomer.getDocument(),
                expectedCustomer.getEmail(),
                Fixture.positiveNumber().doubleValue()
        );

        when(customerGateway.existsByDocument(any())).thenReturn(false);
        when(customerGateway.existsByEmail(any())).thenReturn(true);

        final var actualException = assertThrows(NotificationException.class, () -> useCase.execute(aCommand));
        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());

        verify(customerGateway, never()).create(any());
        verify(customerWalletExternal, never()).createWallet(any());
    }
}