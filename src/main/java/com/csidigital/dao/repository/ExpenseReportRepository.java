package com.csidigital.dao.repository;

import com.csidigital.dao.entity.ExpenseReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseReportRepository extends JpaRepository<ExpenseReport, Long> {

    @Modifying
    @Query(value = " UPDATE expense_report SET request_status = 'VALIDATED' WHERE id =:id", nativeQuery = true)
    void updateStatusToValidatedById(@Param("id") Long id);

    @Modifying
    @Query(value = " UPDATE expense_report SET request_status = 'REJECTED' WHERE id =:id", nativeQuery = true)
    void updateStatusToRejectedById(@Param("id") Long id);
}
