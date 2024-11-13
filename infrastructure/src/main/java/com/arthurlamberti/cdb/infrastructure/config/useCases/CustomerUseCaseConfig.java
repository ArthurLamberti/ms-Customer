package com.arthurlamberti.cdb.infrastructure.config.useCases;

import com.arthurlamberti.cdb.customer.CustomerGateway;
import com.arthurlamberti.cdb.customer.create.CreateCustomerUseCase;
import com.arthurlamberti.cdb.customer.create.DefaultCreateCustomerUseCase;
import com.arthurlamberti.cdb.customer.retrieve.list.DefaultListCustomerUsecase;
import com.arthurlamberti.cdb.customer.retrieve.list.ListCustomerUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerUseCaseConfig {

    private final CustomerGateway customerGateway;

    public CustomerUseCaseConfig(final CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    @Bean
    public CreateCustomerUseCase createCustomerUseCase(){
        return new DefaultCreateCustomerUseCase(customerGateway);
    }

    @Bean
    public ListCustomerUsecase listCustomerUsecase(){
        return new DefaultListCustomerUsecase(customerGateway);
    }
}
