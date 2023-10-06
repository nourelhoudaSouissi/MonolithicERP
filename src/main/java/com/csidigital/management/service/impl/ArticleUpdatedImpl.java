package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.ArticleUpdated;
import com.csidigital.dao.repository.ArticleUpdatedRepository;
import com.csidigital.management.service.ArticleUpdatedService;
import com.csidigital.shared.dto.request.ArUpdatedRequest;
import com.csidigital.shared.dto.response.ArticleUpdatedResponse;
import com.csidigital.shared.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ArticleUpdatedImpl implements ArticleUpdatedService {

    @Autowired
    private ArticleUpdatedRepository articleUpdatedRepository;
    @Autowired
    private ModelMapper modelMapper;
   @Override
    public ArticleUpdatedResponse createArticleUpdated(ArUpdatedRequest request) {

       ArticleUpdated articleUpdated = modelMapper.map(request, ArticleUpdated.class);
       ArticleUpdated articleUpdatedSaved = articleUpdatedRepository.save(articleUpdated);
       return modelMapper.map(articleUpdatedSaved, ArticleUpdatedResponse.class);
    }

    @Override
    public List<ArticleUpdatedResponse> getAllArticlesUpdated() {
        List<ArticleUpdated> articleUpdated = articleUpdatedRepository.findAll();
        List<ArticleUpdatedResponse> articleUpdatedList = new ArrayList<>();

        for (ArticleUpdated articleUpdateds :articleUpdated ) {
            ArticleUpdatedResponse response = modelMapper.map(articleUpdateds, ArticleUpdatedResponse.class);
            articleUpdatedList.add(response);
        }

        return articleUpdatedList;
    }

    @Override
    public ArticleUpdatedResponse getArticleUpdatedById(Long id) {
        ArticleUpdated articleUpdated =articleUpdatedRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("ArticleUpdated with id " +id+ " not found"));
        ArticleUpdatedResponse articleUpdatedResponse = modelMapper.map(articleUpdated, ArticleUpdatedResponse.class);
        return articleUpdatedResponse;
    }

    @Override
    public ArticleUpdatedResponse UpdateArticleUpdatedResponse(ArUpdatedRequest request, Long id) {
        ArticleUpdated existingArticleUpdated = articleUpdatedRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("ArticleUpdated with id: " + id + " not found"));
        modelMapper.map(request, existingArticleUpdated);
        ArticleUpdated savedArticleUpdated = articleUpdatedRepository.save(existingArticleUpdated);
        return modelMapper.map(savedArticleUpdated, ArticleUpdatedResponse.class);
    }

    @Override
    public void deleteArticleUpdated(Long id) {
       articleUpdatedRepository.deleteById(id);

    }
}
