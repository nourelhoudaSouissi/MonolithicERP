package com.csidigital.dao.repository;

import com.csidigital.dao.entity.ArticleClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleClientRepository extends JpaRepository<ArticleClient,Long> {
}
