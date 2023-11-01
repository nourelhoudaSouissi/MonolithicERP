package com.csidigital.dao.repository;

import com.csidigital.dao.entity.BankReconciliation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankReconciliationRepository extends JpaRepository<BankReconciliation, Long> {
}
