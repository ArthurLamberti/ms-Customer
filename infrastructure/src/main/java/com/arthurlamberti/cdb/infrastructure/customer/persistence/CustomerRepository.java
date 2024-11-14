package com.arthurlamberti.cdb.infrastructure.customer.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerJpaEntity, String> {
    boolean existsByDocument(String document);
    boolean existsByEmail(String email);

}
