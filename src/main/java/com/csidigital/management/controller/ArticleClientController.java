package com.csidigital.management.controller;

import com.csidigital.management.service.impl.ArticleClientImpl;
import com.csidigital.shared.dto.request.ArticleClientRequest;
import com.csidigital.shared.dto.response.ArticleClientResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Transactional
@RestController
@RequestMapping("/crm/articleClient")
public class ArticleClientController {
    @Autowired
    private ArticleClientImpl articleClient;
    @GetMapping("/getArticles")
    public List<ArticleClientResponse> getAll() {
        return articleClient.getAllArticleClient();
    }
    @GetMapping("/get/{id}")
    public ArticleClientResponse getById(@PathVariable Long id){
        return articleClient.getArticleClientById(id);
    }
    @PostMapping("/add")
    public ArticleClientResponse createArticle(@Valid @RequestBody ArticleClientRequest articleRequest){
        return articleClient.createArticleClient(articleRequest);
    }
    @PutMapping("/update/{id}")
    public ArticleClientResponse updateArticle(@Valid @RequestBody ArticleClientRequest ArticleRequest,
                                               @PathVariable Long id){
        return articleClient.UpdateArticleClient(ArticleRequest, id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteArticle(@PathVariable Long id){
        articleClient.deleteArticleClient(id);
    }
}
