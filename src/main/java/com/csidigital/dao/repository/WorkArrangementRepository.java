package com.csidigital.dao.repository;

import com.csidigital.dao.entity.WorkArrangement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkArrangementRepository extends JpaRepository<WorkArrangement,Long> {
}
