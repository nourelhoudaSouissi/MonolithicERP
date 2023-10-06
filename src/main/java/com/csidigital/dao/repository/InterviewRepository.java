package com.csidigital.dao.repository;

import com.csidigital.dao.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InterviewRepository extends JpaRepository<Interview, Long> {
    @Modifying
    @Query(value = " UPDATE interview SET interview_status = 'PLANNED' WHERE id =:id", nativeQuery = true)
    void updateStatusToPlannedById(@Param("id") Long id);

    @Modifying
    @Query(value = " UPDATE interview SET interview_status = 'ENDED' WHERE id =:id", nativeQuery = true)
    void updateStatusToEndedById(@Param("id") Long id);

    @Modifying
    @Query(value = " UPDATE interview SET interview_status = 'CANCELLED' WHERE id =:id", nativeQuery = true)
    void updateStatusToCancelledById(@Param("id") Long id);
}