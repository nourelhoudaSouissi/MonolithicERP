package com.csidigital.dao.repository;


import com.csidigital.dao.entity.EndorsementClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EndorsementClientRepository extends JpaRepository<EndorsementClient,Long> {

}
