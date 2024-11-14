package com.arthurlamberti.cdb.infrastructure.customer;

import com.arthurlamberti.cdb.Fixture;
import com.arthurlamberti.cdb.MySQLGatewayTest;
import com.arthurlamberti.cdb.customer.Customer;
import com.arthurlamberti.cdb.customer.CustomerGateway;
import com.arthurlamberti.cdb.customer.CustomerID;
import com.arthurlamberti.cdb.customer.retrieve.list.ListCustomerUsecase;
import com.arthurlamberti.cdb.infrastructure.customer.persistence.CustomerJpaEntity;
import com.arthurlamberti.cdb.infrastructure.customer.persistence.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@MySQLGatewayTest
public class CustomerMySQLGatewayTest {

    @Autowired
    private CustomerGateway customerGateway;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void givenAValidCustomer_whenCallsCreateCustomer_shouldPersistIt() {
        final var expectedName = Fixture.name();
        final var expectedDocument = Fixture.document();
        final var expectedEmail = Fixture.email();

        final var aCustomer = Customer.newCustomer(expectedName, expectedDocument, expectedEmail);
        final var expectedId = aCustomer.getId();

        assertEquals(0, customerRepository.count());
        final var actualCustomer = customerGateway.create(aCustomer);

        assertEquals(1, customerRepository.count());
        assertEquals(expectedName, actualCustomer.getName());
        assertEquals(expectedDocument, actualCustomer.getDocument());
        assertEquals(expectedEmail, actualCustomer.getEmail());

        final var persistedCustomer = customerRepository.findById(expectedId.getValue()).get();
        assertEquals(expectedName, persistedCustomer.getName());
        assertEquals(expectedDocument, persistedCustomer.getDocument());
        assertEquals(expectedEmail, persistedCustomer.getEmail());
    }

    @Test
    public void givenAPrePersistedData_whenCallFindAll_shouldReturnAList() {
        final var expectedName = Fixture.name();
        final var expectedDocument = Fixture.document();
        final var expectedEmail = Fixture.email();
        final var aCustomer = Customer.newCustomer(expectedName, expectedDocument, expectedEmail);

        assertEquals(0, customerRepository.count());
        customerRepository.saveAndFlush(CustomerJpaEntity.from(aCustomer));
        assertEquals(1, customerRepository.count());

        final var actualResult = customerGateway.findAll();
        final var firstCustomer = actualResult.get(0);

        assertEquals(expectedName, firstCustomer.getName());
        assertEquals(expectedDocument, firstCustomer.getDocument());
        assertEquals(expectedEmail, firstCustomer.getEmail());
    }


    @Test
    public void givenAPrePersistedDataAndValidDocument_whenCallExistsByDocument_shouldReturnTrue() {
        final var expectedName = Fixture.name();
        final var expectedDocument = Fixture.document();
        final var expectedEmail = Fixture.email();
        final var aCustomer = Customer.newCustomer(expectedName, expectedDocument, expectedEmail);

        assertEquals(0, customerRepository.count());
        customerRepository.saveAndFlush(CustomerJpaEntity.from(aCustomer));
        assertEquals(1, customerRepository.count());

        final var actualResult = customerGateway.existsByDocument(expectedDocument);

        assertTrue(actualResult);
    }

    @Test
    public void givenAPrePersistedDataAndInvalidDocument_whenCallExistsByDocument_shouldReturnFalse() {
        final var expectedName = Fixture.name();
        final var expectedDocument = Fixture.document();
        final var expectedEmail = Fixture.email();
        final var aCustomer = Customer.newCustomer(expectedName, expectedDocument, expectedEmail);

        assertEquals(0, customerRepository.count());
        customerRepository.saveAndFlush(CustomerJpaEntity.from(aCustomer));
        assertEquals(1, customerRepository.count());

        final var actualResult = customerGateway.existsByDocument("000000000000");

        assertFalse(actualResult);
    }

    @Test
    public void givenAPrePersistedDataAndValidEmail_whenCallExistsByEmail_shouldReturnTrue() {
        final var expectedName = Fixture.name();
        final var expectedDocument = Fixture.document();
        final var expectedEmail = Fixture.email();
        final var aCustomer = Customer.newCustomer(expectedName, expectedDocument, expectedEmail);

        assertEquals(0, customerRepository.count());
        customerRepository.saveAndFlush(CustomerJpaEntity.from(aCustomer));
        assertEquals(1, customerRepository.count());

        final var actualResult = customerGateway.existsByEmail(expectedEmail);

        assertTrue(actualResult);
    }

    @Test
    public void givenAPrePersistedDataAndInvalidEmail_whenCallExistsByEmail_shouldReturnFalse() {
        final var expectedName = Fixture.name();
        final var expectedDocument = Fixture.document();
        final var expectedEmail = Fixture.email();
        final var aCustomer = Customer.newCustomer(expectedName, expectedDocument, expectedEmail);

        assertEquals(0, customerRepository.count());
        customerRepository.saveAndFlush(CustomerJpaEntity.from(aCustomer));
        assertEquals(1, customerRepository.count());

        final var actualResult = customerGateway.existsByDocument("0000000@gmail.com");

        assertFalse(actualResult);
    }

}