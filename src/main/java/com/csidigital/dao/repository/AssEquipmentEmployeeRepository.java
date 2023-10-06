package com.csidigital.dao.repository;

import com.csidigital.dao.entity.AssEquipmentEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AssEquipmentEmployeeRepository extends JpaRepository<AssEquipmentEmployee,Long> {

}
