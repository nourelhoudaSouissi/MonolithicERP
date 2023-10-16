package com.csidigital.dao.repository;

import com.csidigital.dao.entity.RequirementRefSequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequirementSequenceRepository extends JpaRepository<RequirementRefSequence, Long> {
}
