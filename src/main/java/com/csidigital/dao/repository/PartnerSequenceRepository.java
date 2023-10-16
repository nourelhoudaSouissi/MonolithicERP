package com.csidigital.dao.repository;

import com.csidigital.dao.entity.PartnerReferenceSequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerSequenceRepository extends JpaRepository<PartnerReferenceSequence, Long> {
}
