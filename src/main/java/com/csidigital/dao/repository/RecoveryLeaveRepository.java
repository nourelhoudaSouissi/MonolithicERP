package com.csidigital.dao.repository;

import com.csidigital.dao.entity.RecoveryLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecoveryLeaveRepository extends JpaRepository<RecoveryLeave,Long> {
    @Modifying
    @Query(value = " UPDATE recovery_leave SET request_status = 'VALIDATED' WHERE id =:id", nativeQuery = true)
    void updateStatusToValidatedById(@Param("id") Long id);

    @Modifying
    @Query(value = " UPDATE recovery_leave SET request_status = 'REJECTED' WHERE id =:id", nativeQuery = true)
    void updateStatusToRejectedById(@Param("id") Long id);
    @Query(value = "SELECT COUNT(*) FROM recovery_leave WHERE request_status = 'VALIDATED'", nativeQuery = true)
    int countValidated();
    @Query(value = "SELECT COUNT(*) FROM recovery_leave WHERE request_status = 'REJECTED'", nativeQuery = true)
    int countRejected();
    @Query(value = "SELECT COUNT(*) FROM recovery_leave WHERE request_status = 'PENDING'", nativeQuery = true)
    int countPending();
}
