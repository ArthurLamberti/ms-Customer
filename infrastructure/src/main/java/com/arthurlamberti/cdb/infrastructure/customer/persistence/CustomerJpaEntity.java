package com.arthurlamberti.cdb.infrastructure.customer.persistence;

import com.arthurlamberti.cdb.customer.Customer;
import com.arthurlamberti.cdb.customer.CustomerID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "customers")
@Table(name = "customers")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CustomerJpaEntity {

    @Id
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "document", nullable = false)
    private String document;

    @Column(name = "email", nullable = false)
    private String email;

    public static CustomerJpaEntity from(final Customer aCustomer) {
        return new CustomerJpaEntity(
                aCustomer.getId().getValue(),
                aCustomer.getName(),
                aCustomer.getDocument(),
                aCustomer.getEmail()
        );
    }

    public Customer toAggregate() {
        return Customer.with(
                CustomerID.from(this.id),
                this.name,
                this.document,
                this.email
        );
    }
}
