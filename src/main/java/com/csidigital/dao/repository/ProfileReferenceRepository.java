package com.csidigital.dao.repository;

import com.csidigital.dao.entity.ProfileReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileReferenceRepository extends JpaRepository<ProfileReference, Long> {
}
