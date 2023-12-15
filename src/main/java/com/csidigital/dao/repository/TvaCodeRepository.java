package com.csidigital.dao.repository;

import com.csidigital.dao.entity.TvaCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TvaCodeRepository extends JpaRepository<TvaCode, Long> {
}
