package com.csidigital.dao.repository;


import com.csidigital.dao.entity.AdministrativeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrativeDataRepository extends JpaRepository<AdministrativeData,Long> {

}
