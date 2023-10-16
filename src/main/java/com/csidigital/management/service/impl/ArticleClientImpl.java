package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.ArticleClient;
import com.csidigital.dao.repository.ArticleClientRepository;
import com.csidigital.management.service.ArticleClientService;
import com.csidigital.shared.dto.request.ArticleClientRequest;
import com.csidigital.shared.dto.response.ArticleClientResponse;
import com.csidigital.shared.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class ArticleClientImpl implements ArticleClientService {

    @Autowired
    private ArticleClientRepository articleClientRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ArticleClientResponse createArticleClient(ArticleClientRequest request) {
        ArticleClient articleClient = modelMapper.map(request,  ArticleClient.class);
        ArticleClient articleClientSaved = articleClientRepository.save(articleClient);
        return modelMapper.map(articleClientSaved, ArticleClientResponse.class);
    }

    @Override
    public List<ArticleClientResponse> getAllArticleClient() {
        List<ArticleClient> articleClient = articleClientRepository.findAll();
        List<ArticleClientResponse>   articleClientList = new ArrayList<>();

        for (ArticleClient articleClients :articleClient ) {
            ArticleClientResponse response = modelMapper.map(articleClients, ArticleClientResponse.class);
            articleClientList.add(response);
        }

        return articleClientList;
    }

    @Override
    public ArticleClientResponse getArticleClientById(Long id) {
      ArticleClient articleClient =articleClientRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("ArticleUpdated with id " +id+ " not found"));
        ArticleClientResponse articleClientResponse = modelMapper.map(articleClient, ArticleClientResponse.class);
        return articleClientResponse;
    }

    @Override
    public ArticleClientResponse UpdateArticleClient(ArticleClientRequest request, Long id) {
        ArticleClient existingArticleClient = articleClientRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("ArticleUpdated with id: " + id + " not found"));
        modelMapper.map(request, existingArticleClient);
        ArticleClient savedArticleClient = articleClientRepository.save(existingArticleClient);
        return modelMapper.map(savedArticleClient, ArticleClientResponse.class);
    }

    @Override
    public void deleteArticleClient(Long id) {
        articleClientRepository.deleteById(id);
    }
}
