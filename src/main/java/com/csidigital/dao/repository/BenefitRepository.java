package com.csidigital.dao.repository;

import com.csidigital.dao.entity.Benefit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BenefitRepository extends JpaRepository<Benefit,Long> {
}
