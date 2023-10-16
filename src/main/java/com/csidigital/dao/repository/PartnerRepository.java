package com.csidigital.dao.repository;

import com.csidigital.dao.entity.Partner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories(basePackages = "com.csidigital.repository", excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = com.csidigital.dao.repository.PartnerRepository.class))
public interface PartnerRepository extends JpaRepository<Partner,Long> {
    @Modifying
    @Query(value = " UPDATE partner SET company_status = 'CLIENT' WHERE id =:id", nativeQuery = true)
    void updateStatusToClient(@Param("id") Long id);
}

