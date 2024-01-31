package com.csidigital.dao.repository;


import com.csidigital.dao.entity.ServiceReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceReferenceRepository extends JpaRepository<ServiceReference,Long> {
}
