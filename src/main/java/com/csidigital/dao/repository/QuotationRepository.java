package com.csidigital.dao.repository;

import com.csidigital.dao.entity.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface QuotationRepository extends JpaRepository<Quotation, Long> {
    /*@Query(value = "SELECT p.payment_mode, p.payment_condition " +
        "FROM partner p , requirement r " +
        "WHERE r.id = :reqId AND r.partner_id = p.id",
        nativeQuery = true)
    Object getPartnerByReqId(@Param("reqId") Long reqId);*/
    @Modifying
    @Query(value = " UPDATE quotation SET quotation_status = 'VALIDATED' WHERE id =:id", nativeQuery = true)
    void updateStatusToAccepted(@Param("id") Long id);
    @Modifying
    @Query(value = " UPDATE quotation SET quotation_status = 'IN_PROGRESS' WHERE id =:id", nativeQuery = true)
    void updateStatusToInProgress(@Param("id") Long id);
    @Modifying
    @Query(value = " UPDATE quotation SET quotation_status = 'REFUSED' WHERE id =:id", nativeQuery = true)
    void updateStatusToRefused(@Param("id") Long id);
    @Modifying
    @Query(value = " UPDATE quotation SET quotation_status = 'UNANSWERED' WHERE id =:id", nativeQuery = true)
    void updateStatusToUnanswered(@Param("id") Long id);

}
