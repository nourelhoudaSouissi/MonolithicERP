package com.csidigital.dao.repository;

import com.csidigital.dao.entity.ProfileUpdated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileUpdatedRepository extends JpaRepository<ProfileUpdated,Long> {
}
