package com.csidigital.repository;


import com.csidigital.models.Customer;
import com.csidigital.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    Customer findByEmail(String email);

    Optional<Customer> findByUsername(String username);

    List<Customer> findByUser(User user);

    Customer findByConfirmationToken ( String confirmationToken);

    Customer findByPasswordResetToken(String passwordResetToken);
    Optional<Customer> findByUserUsername(String username);

}
