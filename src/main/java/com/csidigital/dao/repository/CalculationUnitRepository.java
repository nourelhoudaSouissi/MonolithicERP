package com.csidigital.dao.repository;

import com.csidigital.dao.entity.CalculationUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculationUnitRepository extends JpaRepository<CalculationUnit,Long> {
}
