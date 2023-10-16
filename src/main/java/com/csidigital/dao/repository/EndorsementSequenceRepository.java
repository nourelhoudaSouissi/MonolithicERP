package com.csidigital.dao.repository;

import com.csidigital.dao.entity.EndorsementRefSequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EndorsementSequenceRepository extends JpaRepository<EndorsementRefSequence, Long> {
}
