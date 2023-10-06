package com.csidigital.dao.repository;

import com.csidigital.dao.entity.SkillsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillsCategoryRepository extends JpaRepository<SkillsCategory,Long> {
}
