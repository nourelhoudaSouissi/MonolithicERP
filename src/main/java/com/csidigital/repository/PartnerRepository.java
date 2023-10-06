package com.csidigital.repository;


import com.csidigital.models.Partner;
import com.csidigital.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartnerRepository extends JpaRepository<Partner,Long> {

    List<Partner> findByUserpId(Long userId);
    Optional<Partner> findByIdAndUserpId(Long id, Long userId);
    void deleteByIdAndUserpId(Long id, Long userId);

    List<Partner> findByUserp(User user);
}

