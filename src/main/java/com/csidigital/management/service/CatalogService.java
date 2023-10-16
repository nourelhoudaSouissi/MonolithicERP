package com.csidigital.management.service;

import com.csidigital.dao.entity.Catalog;
import com.csidigital.shared.dto.request.CatalogRequest;
import com.csidigital.shared.dto.response.CatalogResponse;
import com.csidigital.shared.dto.response.ProfileResponse;

import java.util.List;

public interface CatalogService {
    CatalogResponse createCatalog(CatalogRequest request);
    List<CatalogResponse> getAllCatalogs();
    CatalogResponse getCatalogById(Long id);
    CatalogResponse getCatalogByProfileId(Long profileId);
    CatalogResponse updateCatalog(CatalogRequest request, Long id );
    List<ProfileResponse> getCatalogProfilesById(Long id);
    void convertDates(Catalog catalog, CatalogResponse response);
    void deleteCatalog(Long id);
}
