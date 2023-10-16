package com.csidigital.dao.repository;

import com.csidigital.dao.entity.QuotationReferenceSequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotationSequenceRepository extends JpaRepository<QuotationReferenceSequence, Long> {
}
