package com.csidigital.dao.repository;

import com.csidigital.dao.entity.OrderReferenceSequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderSequenceRepository extends JpaRepository<OrderReferenceSequence, Long> {
}
