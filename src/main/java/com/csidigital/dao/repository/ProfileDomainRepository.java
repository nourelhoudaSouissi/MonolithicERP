package com.csidigital.dao.repository;


import com.csidigital.dao.entity.ProfileDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileDomainRepository extends JpaRepository<ProfileDomain, Long> {
}
