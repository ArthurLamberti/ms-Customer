package com.arthurlamberti.cdb.infrastructure.customer;

import com.arthurlamberti.cdb.customer.Customer;
import com.arthurlamberti.cdb.customer.CustomerGateway;
import com.arthurlamberti.cdb.infrastructure.customer.persistence.CustomerJpaEntity;
import com.arthurlamberti.cdb.infrastructure.customer.persistence.CustomerRepository;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.requireNonNull;

@Component
public class CustomerMySQLGateway implements CustomerGateway {

    private final CustomerRepository customerRepository;

    public CustomerMySQLGateway(final CustomerRepository customerRepository){
        this.customerRepository = requireNonNull(customerRepository);
    }

    @Override
    public Customer create(Customer aCustomer) {
        return this.customerRepository.save(CustomerJpaEntity.from(aCustomer)).toAggregate();
    }

    @Override
    public List<Customer> findAll() {
        return this.customerRepository.findAll()
                .stream()
                .map(CustomerJpaEntity::toAggregate)
                .toList();
    }

    @Override
    public boolean existsByDocument(String document) {
        return this.customerRepository.existsByDocument(document);
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.customerRepository.existsByEmail(email);
    }
}
