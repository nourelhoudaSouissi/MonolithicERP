package com.csidigital.dao.repository;

import com.csidigital.dao.entity.Phase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhaseRepository extends JpaRepository<Phase,Long> {
}
