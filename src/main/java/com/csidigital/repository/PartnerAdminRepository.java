package com.csidigital.repository;


import com.csidigital.models.Admin;
import com.csidigital.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartnerAdminRepository extends JpaRepository<Admin,Long> {

    List<Admin> findByUserpId(Long userId);
    Optional<Admin> findByIdAndUserpId(Long id, Long userId);
    void deleteByIdAndUserpId(Long id, Long userId);

    List<Admin> findByUserp(User user);
}

