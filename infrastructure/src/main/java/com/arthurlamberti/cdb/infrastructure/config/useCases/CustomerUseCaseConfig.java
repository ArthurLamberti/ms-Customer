package com.arthurlamberti.cdb.infrastructure.config.useCases;

import com.arthurlamberti.cdb.adapters.feing.CustomerWalletExternal;
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
    private final CustomerWalletExternal customerWalletExternal;

    public CustomerUseCaseConfig(
            final CustomerGateway customerGateway,
            final CustomerWalletExternal customerWalletExternal) {
        this.customerGateway = customerGateway;
        this.customerWalletExternal = customerWalletExternal;
    }

    @Bean
    public CreateCustomerUseCase createCustomerUseCase(){
        return new DefaultCreateCustomerUseCase(customerGateway, customerWalletExternal);
    }

    @Bean
    public ListCustomerUsecase listCustomerUsecase(){
        return new DefaultListCustomerUsecase(customerGateway);
    }
}
