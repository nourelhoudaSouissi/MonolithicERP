package com.csidigital.management.service;

import com.csidigital.shared.dto.request.ArticleClientRequest;
import com.csidigital.shared.dto.response.ArticleClientResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ArticleClientService {
    ArticleClientResponse createArticleClient(ArticleClientRequest request);
    List<ArticleClientResponse> getAllArticleClient();
    ArticleClientResponse getArticleClientById(Long id);

    ArticleClientResponse UpdateArticleClient(ArticleClientRequest request, Long id);

    void deleteArticleClient(Long id);
}
