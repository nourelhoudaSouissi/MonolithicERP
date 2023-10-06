package com.csidigital.dao.repository;

import com.csidigital.dao.entity.TechnicalFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicalFileRepository extends JpaRepository<TechnicalFile,Long> {
}
