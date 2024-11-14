package com.arthurlamberti.cdb.application.customer.create;

import com.arthurlamberti.cdb.Fixture;
import com.arthurlamberti.cdb.IntegrationTest;
import com.arthurlamberti.cdb.customer.CustomerGateway;
import com.arthurlamberti.cdb.customer.create.CreateCustomerCommand;
import com.arthurlamberti.cdb.customer.create.CreateCustomerUseCase;
import com.arthurlamberti.cdb.exceptions.NotificationException;
import com.arthurlamberti.cdb.infrastructure.customer.persistence.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@IntegrationTest
public class CreateCustomerUseCaseIT {

    @Autowired
    private CreateCustomerUseCase useCase;

    @Autowired
    private CustomerRepository customerRepository;

    @SpyBean
    private CustomerGateway customerGateway;

    @Test
    public void givenValidCommand_whenCallsCreateCustomer_shouldReturnCustomerId() {
        final var expectedName = Fixture.name();
        final var expectedDocument = Fixture.document();
        final var expectedEmail = Fixture.email();

        assertEquals(0, customerRepository.count());

        final var aCommand = CreateCustomerCommand.with(
                expectedName,
                expectedDocument,
                expectedEmail
        );

        final var actualResult = useCase.execute(aCommand);

        assertNotNull(actualResult);
        assertNotNull(actualResult.id());
        assertEquals(1, customerRepository.count());

        final var actualCustomer = customerRepository.findById(actualResult.id()).get();
        assertEquals(expectedName, actualCustomer.getName());
        assertEquals(expectedDocument, actualCustomer.getDocument());
        assertEquals(expectedEmail, actualCustomer.getEmail());
        verify(customerGateway, times(1)).create(any());
    }

    @Test
    public void givenAnInvalidNullName_whenCallsCreateCustomer_thenShouldReturnException() {
        final String expectedName = null;
        final var expectedDocument = Fixture.document();
        final var expectedEmail = Fixture.email();

        final var expectedErrorMessage = "'name' should not be null";
        final var expectedErrorCount = 1;

        assertEquals(0, customerRepository.count());
        final var aCommand = CreateCustomerCommand.with(expectedName, expectedDocument, expectedEmail);

        final var actualException = assertThrows(NotificationException.class, () -> useCase.execute(aCommand));
        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());

        verify(customerGateway, times(0)).create(any());
    }

    @Test
    public void givenAnInvalidEmptyName_whenCallsCreateCustomer_thenShouldReturnException() {
        final var expectedName = " ";
        final var expectedDocument = Fixture.document();
        final var expectedEmail = Fixture.email();

        final var expectedErrorMessage = "'name' should not be empty";
        final var expectedErrorCount = 1;

        assertEquals(0, customerRepository.count());
        final var aCommand = CreateCustomerCommand.with(expectedName, expectedDocument, expectedEmail);

        final var actualException = assertThrows(NotificationException.class, () -> useCase.execute(aCommand));
        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());

        verify(customerGateway, times(0)).create(any());
    }

    @Test
    public void givenAnInvalidNullDocument_whenCallsCreateCustomer_thenShouldReturnException() {
        final var expectedName = Fixture.name();
        final String expectedDocument = null;
        final var expectedEmail = Fixture.email();

        final var expectedErrorMessage = "'document' should not be null";
        final var expectedErrorCount = 1;

        assertEquals(0, customerRepository.count());
        final var aCommand = CreateCustomerCommand.with(expectedName, expectedDocument, expectedEmail);

        final var actualException = assertThrows(NotificationException.class, () -> useCase.execute(aCommand));
        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());

        verify(customerGateway, times(0)).create(any());
    }

    @Test
    public void givenAnInvalidEmptyDocument_whenCallsCreateCustomer_thenShouldReturnException() {
        final var expectedName = Fixture.name();
        final var expectedDocument = " ";
        final var expectedEmail = Fixture.email();

        final var expectedErrorMessage = "'document' should not be empty";
        final var expectedErrorCount = 1;

        assertEquals(0, customerRepository.count());
        final var aCommand = CreateCustomerCommand.with(expectedName, expectedDocument, expectedEmail);

        final var actualException = assertThrows(NotificationException.class, () -> useCase.execute(aCommand));
        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());

        verify(customerGateway, times(0)).create(any());
    }

    @Test
    public void givenAnInvalidNullEmail_whenCallsCreateCustomer_thenShouldReturnException() {
        final var expectedName = Fixture.name();
        final var expectedDocument = Fixture.document();
        final String expectedEmail = null;

        final var expectedErrorMessage = "'email' should not be null";
        final var expectedErrorCount = 1;

        assertEquals(0, customerRepository.count());
        final var aCommand = CreateCustomerCommand.with(expectedName, expectedDocument, expectedEmail);

        final var actualException = assertThrows(NotificationException.class, () -> useCase.execute(aCommand));
        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());

        verify(customerGateway, times(0)).create(any());
    }

    @Test
    public void givenAnInvalidEmptyEmail_whenCallsCreateCustomer_thenShouldReturnException() {
        final var expectedName = Fixture.name();
        final var expectedDocument = Fixture.document();
        final var expectedEmail = " ";

        final var expectedErrorMessage = "'email' should not be empty";
        final var expectedErrorCount = 1;

        assertEquals(0, customerRepository.count());
        final var aCommand = CreateCustomerCommand.with(expectedName, expectedDocument, expectedEmail);

        final var actualException = assertThrows(NotificationException.class, () -> useCase.execute(aCommand));
        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());

        verify(customerGateway, times(0)).create(any());
    }

    @Test
    public void givenAnDuplicatedEmail_whenCallsCreateCustomer_thenShouldReturnException() {
        final var expectedName = Fixture.name();
        final var expectedDocument = Fixture.document();
        final var expectedEmail = Fixture.email();

        final var expectedErrorMessage = "Customer already exists with email " + expectedEmail;
        final var expectedErrorCount = 1;

        assertEquals(0, customerRepository.count());
        insertCustomer(Fixture.document(), expectedEmail);
        assertEquals(1, customerRepository.count());

        final var aCommand = CreateCustomerCommand.with(expectedName, expectedDocument, expectedEmail);

        final var actualException = assertThrows(NotificationException.class, () -> useCase.execute(aCommand));
        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());

        verify(customerGateway, times(1)).create(any());
    }


    @Test
    public void givenAnDuplicatedDocument_whenCallsCreateCustomer_thenShouldReturnException() {
        final var expectedName = Fixture.name();
        final var expectedDocument = Fixture.document();
        final var expectedEmail = Fixture.email();

        final var expectedErrorMessage = "Customer already exists with document " + expectedDocument;
        final var expectedErrorCount = 1;

        assertEquals(0, customerRepository.count());
        insertCustomer(expectedDocument, Fixture.email());
        assertEquals(1, customerRepository.count());

        final var aCommand = CreateCustomerCommand.with(expectedName, expectedDocument, expectedEmail);

        final var actualException = assertThrows(NotificationException.class, () -> useCase.execute(aCommand));
        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getFirstError().get().message());

        verify(customerGateway, times(1)).create(any());
    }

    private void insertCustomer(final String document, final String email) {
        final var aCommand = CreateCustomerCommand.with(Fixture.name(), document, email);
        useCase.execute(aCommand);
    }

}
