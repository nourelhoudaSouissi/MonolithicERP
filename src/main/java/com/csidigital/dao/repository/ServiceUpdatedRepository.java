package com.csidigital.dao.repository;

import com.csidigital.dao.entity.ServiceUpdated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceUpdatedRepository extends JpaRepository<ServiceUpdated,Long> {
}
