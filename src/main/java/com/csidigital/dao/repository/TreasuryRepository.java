package com.csidigital.dao.repository;

import com.csidigital.dao.entity.Treasury;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreasuryRepository extends JpaRepository<Treasury,Long> {
}
