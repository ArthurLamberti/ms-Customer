package com.arthurlamberti.cdb.application.customer.retrieve;

import com.arthurlamberti.cdb.IntegrationTest;
import com.arthurlamberti.cdb.customer.Customer;
import com.arthurlamberti.cdb.customer.retrieve.list.ListCustomerUsecase;
import com.arthurlamberti.cdb.infrastructure.customer.persistence.CustomerJpaEntity;
import com.arthurlamberti.cdb.infrastructure.customer.persistence.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@IntegrationTest
public class ListCustomerUseCaseIT {

    @Autowired
    private ListCustomerUsecase usecase;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void mockUp() {
        final var customers = Stream.of(
                        Customer.newCustomer("Joao", "123456789", "joao@email.com"),
                        Customer.newCustomer("Maria", "987654321", "maria@email.com"),
                        Customer.newCustomer("Pedro", "123123123", "pedro@email.com"),
                        Customer.newCustomer("Ana", "321321321", "ana@email.com"),
                        Customer.newCustomer("Lucas", "456456456", "lucas@email.com"),
                        Customer.newCustomer("Carla", "789789789", "carla@email.com"),
                        Customer.newCustomer("Rafael", "112233445", "rafael@email.com"),
                        Customer.newCustomer("Fernanda", "998877665", "fernanda@email.com"),
                        Customer.newCustomer("Gustavo", "556677889", "gustavo@email.com"),
                        Customer.newCustomer("Camila", "667788990", "camila@email.com"),
                        Customer.newCustomer("Renato", "123987456", "renato@email.com")
                )
                .map(CustomerJpaEntity::from)
                .toList();
        customerRepository.saveAllAndFlush(customers);
    }

    @Test
    public void givenAValidCommand_whenCallList_shouldReturnAllCustomers(){
        final var totalCount = 11;
        final var actualOutput = usecase.execute();

        assertNotNull(actualOutput);
        assertEquals(totalCount, actualOutput.size());
    }
}
