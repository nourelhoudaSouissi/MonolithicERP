package com.csidigital.dao.repository;

import com.csidigital.dao.entity.ExtraDuty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtraDutyRepository extends JpaRepository<ExtraDuty,Long> {
}
