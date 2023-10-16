package com.csidigital.dao.repository;

import com.csidigital.dao.entity.ContractClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractClientRepository extends JpaRepository<ContractClient, Long> {
    @Modifying
    @Query(value = "UPDATE contract_client SET contract_status =:contractStatus WHERE id =:id", nativeQuery = true)
    void updateStatusById(@Param("id") Long id, @Param("contractStatus") String contractStatus);

    @Modifying
    @Query(value = " UPDATE contract_client SET contract_status = 'ACCEPTED' WHERE id =:id", nativeQuery = true)
    void updateStatusToAcceptedById(@Param("id") Long id);

    @Modifying
    @Query(value = " UPDATE contract_client SET contract_status = 'REFUSED' WHERE id =:id", nativeQuery = true)
    void updateStatusToRefusedById(@Param("id") Long id);

    @Modifying
    @Query(value = " UPDATE contract_client SET contract_status = 'SENT' WHERE id =:id", nativeQuery = true)
    void updateStatusToSentById(@Param("id") Long id);



}
