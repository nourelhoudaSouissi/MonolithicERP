package com.csidigital.dao.repository;

import com.csidigital.dao.entity.PaymentTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentTermRepository extends JpaRepository<PaymentTerm, Long> {

}
