package com.csidigital.management.service;

import com.csidigital.shared.dto.request.ArticleRequest;
import com.csidigital.shared.dto.response.ArticleResponse;

import java.util.List;

public interface ArticleService {
    ArticleResponse createArticle(ArticleRequest request);
    List<ArticleResponse> getAllArticles();
    ArticleResponse getArticleById(Long id);

    ArticleResponse updateArticle(ArticleRequest request, Long id);

    void deleteArticle(Long id);
    void getArticleDescription(String articleTitle);


}
