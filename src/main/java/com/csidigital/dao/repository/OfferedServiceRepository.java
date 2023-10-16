package com.csidigital.dao.repository;

import com.csidigital.dao.entity.OfferedService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferedServiceRepository extends JpaRepository<OfferedService,Long> {
}
