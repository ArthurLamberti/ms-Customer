package com.arthurlamberti.cdb.customer;

import com.arthurlamberti.cdb.Fixture;
import com.arthurlamberti.cdb.exceptions.NotificationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    @Test
    void givenValidData_whenCreatingCustomer_thenCustomerIsCreatedSuccessfully() {
        final var expectedName = Fixture.name();
        final var expectedDocument = Fixture.document();
        final var expectedEmail = Fixture.email();

        final var actualCustomer = Customer.newCustomer(expectedName, expectedDocument, expectedEmail);
        assertNotNull(actualCustomer);
        assertNotNull(actualCustomer.getId());
        assertEquals(expectedName, actualCustomer.getName());
        assertEquals(expectedDocument, actualCustomer.getDocument());
        assertEquals(expectedEmail, actualCustomer.getEmail());
    }

    @Test
    void givenNullName_whenCreatingCustomer_thenThrowsException() {
        final String expectedName = null;
        final var expectedDocument = Fixture.document();
        final var expectedEmail = Fixture.email();

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";

        final var actualError = assertThrows(NotificationException.class,
                () -> Customer.newCustomer(expectedName, expectedDocument, expectedEmail));

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        actualError.getFirstError().ifPresent(
                error -> assertEquals(expectedErrorMessage, error.message())
        );
    }

    @Test
    void givenEmptyName_whenCreatingCustomer_thenThrowsException() {
        final var expectedName = " ";
        final var expectedDocument = Fixture.document();
        final var expectedEmail = Fixture.email();


        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be empty";

        final var actualError = assertThrows(NotificationException.class,
                () -> Customer.newCustomer(expectedName, expectedDocument, expectedEmail));

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        actualError.getFirstError().ifPresent(
                error -> assertEquals(expectedErrorMessage, error.message())
        );
    }

    @Test
    void givenNullDocument_whenCreatingCustomer_thenThrowsException() {
        final var expectedName = Fixture.name();
        final String expectedDocument = null;
        final var expectedEmail = Fixture.email();


        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'document' should not be null";

        final var actualError = assertThrows(NotificationException.class,
                () -> Customer.newCustomer(expectedName, expectedDocument, expectedEmail));

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        actualError.getFirstError().ifPresent(
                error -> assertEquals(expectedErrorMessage, error.message())
        );
    }

    @Test
    void givenEmptyDocument_whenCreatingCustomer_thenThrowsException() {
        final var expectedName = Fixture.name();
        final var expectedDocument = " ";
        final var expectedEmail = Fixture.email();


        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'document' should not be empty";

        final var actualError = assertThrows(NotificationException.class,
                () -> Customer.newCustomer(expectedName, expectedDocument, expectedEmail));

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        actualError.getFirstError().ifPresent(
                error -> assertEquals(expectedErrorMessage, error.message())
        );
    }

    @Test
    void givenNullEmail_whenCreatingCustomer_thenThrowsException() {
        final var expectedName = Fixture.name();
        final var expectedDocument = Fixture.document();
        final String expectedEmail = null;

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'email' should not be null";

        final var actualError = assertThrows(NotificationException.class,
                () -> Customer.newCustomer(expectedName, expectedDocument, expectedEmail));

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        actualError.getFirstError().ifPresent(
                error -> assertEquals(expectedErrorMessage, error.message())
        );
    }

    @Test
    void givenEmptyEmail_whenCreatingCustomer_thenThrowsException() {
        final var expectedName = Fixture.name();
        final var expectedDocument = Fixture.document();
        final var expectedEmail = " ";


        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'email' should not be empty";

        final var actualError = assertThrows(NotificationException.class,
                () -> Customer.newCustomer(expectedName, expectedDocument, expectedEmail));

        assertEquals(expectedErrorCount, actualError.getErrors().size());
        actualError.getFirstError().ifPresent(
                error -> assertEquals(expectedErrorMessage, error.message())
        );
    }
}