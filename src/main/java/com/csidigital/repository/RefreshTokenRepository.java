package com.csidigital.repository;

import java.util.Optional;

import com.csidigital.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.csidigital.models.RefreshToken;
import com.csidigital.models.User;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
  Optional<RefreshToken> findByToken(String token);

  @Modifying
  int deleteByUser(User user);
  @Modifying
  int deleteByCustomer(Customer customer);


}
