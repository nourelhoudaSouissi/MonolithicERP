package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.Catalog;
import com.csidigital.dao.entity.Profile;
import com.csidigital.dao.repository.CatalogRepository;
import com.csidigital.dao.repository.ProfileRepository;
import com.csidigital.management.service.CatalogService;
import com.csidigital.shared.dto.request.CatalogRequest;
import com.csidigital.shared.dto.response.CatalogResponse;
import com.csidigital.shared.dto.response.ProfileResponse;
import com.csidigital.shared.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class CatalogServiceImpl implements CatalogService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CatalogRepository catalogRepository ;
    @Autowired
    private ProfileRepository profileRepository ;
    @Autowired
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    @Override
    public CatalogResponse createCatalog(CatalogRequest request) {
        System.out.println("profiles");
        System.out.println(request.getProfiles());

        Catalog catalog = modelMapper.map(request, Catalog.class);
        catalog.setCreationDate(LocalDate.now());
        System.out.println("HEEEEEEEEEEEEEEEEEEEERE");
        System.out.println(catalog);
        List<Profile> profiles = request.getProfiles();
        if (!profiles.isEmpty()) {
            for (Profile profile : profiles) {
                System.out.println("HEEEEEEEEEEEEEEEEEEEEYYYYY");
                System.out.println(profile);
                profile.setCatalog(catalog);
                profileRepository.save(profile);
            }
        }
        return modelMapper.map(catalogRepository.save(catalog), CatalogResponse.class);
    }

    @Override
    public List<CatalogResponse> getAllCatalogs() {
        List<Catalog> catalogs = catalogRepository.findAll();
        List<CatalogResponse> catalogList = new ArrayList<>();

        for (Catalog catalog : catalogs) {
            CatalogResponse response = modelMapper.map(catalog, CatalogResponse.class);

            //convertDates(catalog, response);

            catalogList.add(response);
        }
        return catalogList;
    }

    @Override
    public CatalogResponse getCatalogById(Long id) {
        Catalog catalog = catalogRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Catalog with id " +id+ " not found"));
        CatalogResponse response = modelMapper.map(catalog, CatalogResponse.class);

        //convertDates(catalog, response);

        return response;
    }

    @Override
    public CatalogResponse getCatalogByProfileId(Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(()-> new ResourceNotFoundException("Requirement with id " +profileId+ " not found"));
        return modelMapper.map(profile.getCatalog(), CatalogResponse.class);
    }

    @Override
    public CatalogResponse updateCatalog(CatalogRequest request, Long id) {
        Catalog existingCatalog = catalogRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Catalog with id: " + id + " not found"));

        modelMapper.map(request, existingCatalog);
        System.out.println(existingCatalog);

        List<Profile> profiles = existingCatalog.getProfiles();
        for (Profile profile : profiles) {
            System.out.println("HEEEEEEEEEEEEEEEEEEEEYYYYY");
            System.out.println(profile);
            profile.setCatalog(existingCatalog);
        }
        Catalog catalog = catalogRepository.save(existingCatalog);

        return modelMapper.map(catalog, CatalogResponse.class);
    }

    @Override
    public List<ProfileResponse> getCatalogProfilesById(Long id) {
        Catalog catalog = catalogRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Catalog with id " +id+ " not found"));
        List<Profile> profiles = catalog.getProfiles();
        List<ProfileResponse> profilesList = new ArrayList<>();
        for (Profile profile : profiles) {
            ProfileResponse response = modelMapper.map(profile, ProfileResponse.class);
            profilesList.add(response);
        }
        return profilesList;
    }

    @Override
    public void convertDates(Catalog catalog, CatalogResponse response) {
        // Convert the startDate to the desired format
        if(catalog.getCreationDate()!=null) {
            String formattedStartDate = catalog.getCreationDate().format(dateFormatter);
            response.setCreationDate(formattedStartDate);
        }

    }

    @Override
    public void deleteCatalog(Long id) {
        catalogRepository.deleteById(id);
    }
}
