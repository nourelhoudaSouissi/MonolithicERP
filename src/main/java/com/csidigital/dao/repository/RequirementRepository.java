package com.csidigital.dao.repository;

import com.csidigital.dao.entity.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequirementRepository extends JpaRepository<Requirement , Long> {
    List<Requirement> findByPartnerId(Long id);
    @Modifying
    @Query(value = " UPDATE requirement SET requirement_status = 'OPEN' WHERE id =:id", nativeQuery = true)
    void updateStatusToOpen(@Param("id") Long id);
    @Modifying
    @Query(value = " UPDATE requirement SET requirement_status = 'IN_PROGRESS' WHERE id =:id", nativeQuery = true)
    void updateStatusToInProgress(@Param("id") Long id);
    @Modifying
    @Query(value = " UPDATE requirement SET requirement_status = 'CLOSED' WHERE id =:id", nativeQuery = true)
    void updateStatusToClosed(@Param("id") Long id);
    @Modifying
    @Query(value = " UPDATE requirement SET requirement_status = 'PARTIALLY_WON' WHERE id =:id", nativeQuery = true)
    void updateStatusToPartiallyWon(@Param("id") Long id);
    @Modifying
    @Query(value = " UPDATE requirement SET requirement_status = 'TOTALLY_WON' WHERE id =:id", nativeQuery = true)
    void updateStatusToTotallyWon(@Param("id") Long id);
    @Modifying
    @Query(value = " UPDATE requirement SET requirement_status = 'PARTIALLY_LOST' WHERE id =:id", nativeQuery = true)
    void updateStatusToPartiallyLost(@Param("id") Long id);
    @Modifying
    @Query(value = " UPDATE requirement SET requirement_status = 'TOTALLY_LOST' WHERE id =:id", nativeQuery = true)
    void updateStatusToTotallyLost(@Param("id") Long id);
    @Modifying
    @Query(value = " UPDATE requirement SET requirement_status = 'ABANDONED' WHERE id =:id", nativeQuery = true)
    void updateStatusToAbandoned(@Param("id") Long id);
}
