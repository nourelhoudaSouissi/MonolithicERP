package com.csidigital.dao.repository;

import com.csidigital.dao.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract,Long> {

    @Modifying
    @Query(value = "UPDATE contract SET contract_status =:contractStatus WHERE id =:id", nativeQuery = true)
    void updateStatusById(@Param("id") Long id, @Param("contractStatus") String contractStatus);

    @Modifying
    @Query(value = " UPDATE contract SET contract_status = 'ACCEPTED' WHERE id =:id", nativeQuery = true)
    void updateStatusToAcceptedById(@Param("id") Long id);

    @Modifying
    @Query(value = " UPDATE contract SET contract_status = 'REFUSED' WHERE id =:id", nativeQuery = true)
    void updateStatusToRefusedById(@Param("id") Long id);

    @Modifying
    @Query(value = " UPDATE contract SET contract_status = 'EXPIRED' WHERE id =:id", nativeQuery = true)
    void updateStatusToExpiredById(@Param("id") Long id);
    @Modifying
    @Query(value = " UPDATE contract SET contract_status = 'SENT' WHERE id =:id", nativeQuery = true)
    void updateStatusToSentById(@Param("id") Long id);

    @Query(value = "SELECT COUNT(*) FROM Contract WHERE contract_status = 'STILL_PENDING'", nativeQuery = true)
    int countContractsByStillPendingStatus();

    @Query(value = "SELECT COUNT(*) FROM Contract WHERE contract_status = 'REFUSED'", nativeQuery = true)
    int countContractsByRefusedStatus();

    @Query(value = "SELECT COUNT(*) FROM Contract WHERE contract_status = 'ACCEPTED'", nativeQuery = true)
    int countContractsByAcceptedStatus();

    @Query(value = "SELECT COUNT(*) FROM Contract WHERE contract_status = 'SENT'", nativeQuery = true)
    int countContractsBySentStatus();

    @Query(value = "SELECT COUNT(*) FROM Contract WHERE contract_status = 'EXPIRED'", nativeQuery = true)
    int countContractsByExpiredStatus();

    @Query(value = "SELECT * FROM Contract WHERE contract_status = 'ACCEPTED'", nativeQuery = true)
    List<Contract> getAllAccepted();


}
