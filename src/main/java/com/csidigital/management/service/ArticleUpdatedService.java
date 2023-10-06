package com.csidigital.management.service;

import com.csidigital.shared.dto.request.ArUpdatedRequest;
import com.csidigital.shared.dto.response.ArticleUpdatedResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticleUpdatedService {
    ArticleUpdatedResponse createArticleUpdated(ArUpdatedRequest request);
    List<ArticleUpdatedResponse> getAllArticlesUpdated();
    ArticleUpdatedResponse getArticleUpdatedById(Long id);

    ArticleUpdatedResponse UpdateArticleUpdatedResponse(ArUpdatedRequest request, Long id);

    void deleteArticleUpdated(Long id);
}
