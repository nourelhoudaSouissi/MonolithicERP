package com.csidigital.dao.repository;

import com.csidigital.dao.entity.ContractRefSequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractSequenceRepository extends JpaRepository<ContractRefSequence, Long>{
}
