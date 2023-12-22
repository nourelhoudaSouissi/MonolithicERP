package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.Catalog;
import com.csidigital.dao.entity.Profile;
import com.csidigital.dao.entity.TvaCode;
import com.csidigital.dao.repository.CatalogRepository;
import com.csidigital.dao.repository.ProfileRepository;
import com.csidigital.dao.repository.ServiceRepository;
import com.csidigital.dao.repository.TvaCodeRepository;
import com.csidigital.management.service.CatalogService;
import com.csidigital.shared.dto.request.CatalogRequest;
import com.csidigital.shared.dto.request.ProfileRequest;
import com.csidigital.shared.dto.request.ServiceRequest;
import com.csidigital.shared.dto.response.CatalogResponse;
import com.csidigital.shared.dto.response.ProfileResponse;
import com.csidigital.shared.dto.response.ServiceResponse;
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
    private ServiceRepository serviceRepository ;

    @Autowired
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
  /*  @Override
    public CatalogResponse createCatalog(CatalogRequest request) {

        Catalog catalog = modelMapper.map(request, Catalog.class);
        catalog.setCreationDate(LocalDate.now());

        List<ProfileRequest> profiles = request.getProfiles();
        List<ServiceRequest> services = request.getServices();

        if (!profiles.isEmpty()) {
            for (ProfileRequest profileRequest : profiles) {
                Profile profile= modelMapper.map(profileRequest, Profile.class);
                profile.setCatalog(catalog);
                profileRepository.save(profile);
            }
        }
        if (!services.isEmpty()) {
            for (ServiceRequest serviceRequest : services) {
                com.csidigital.dao.entity.Service service = modelMapper.map(serviceRequest, com.csidigital.dao.entity.Service.class);
                service.setCatalog(catalog);
                serviceRepository.save(service);
            }
        }

        return modelMapper.map(catalogRepository.save(catalog), CatalogResponse.class);
    /* if (!profiles.isEmpty()) {
            for (Profile profile : profiles) {
                System.out.println("HEEEEEEEEEEEEEEEEEEEEYYYYY");
                System.out.println(profile);
                profile.setCatalog(catalog);
                profileRepository.save(profile);
            }
        }*/
   // }
  @Override
  public CatalogResponse createCatalog(CatalogRequest request) {
      Catalog catalog = modelMapper.map(request, Catalog.class);
      catalog.setCreationDate(LocalDate.now());

      List<ProfileRequest> profiles = request.getProfiles();
      List<ServiceRequest> services = request.getServices();

      if (profiles != null && !profiles.isEmpty()) {
          for (ProfileRequest profileRequest : profiles) {
              Profile profile = modelMapper.map(profileRequest, Profile.class);
              profile.setCatalog(catalog);
              profileRepository.save(profile);
          }
      }

      if (services != null && !services.isEmpty()) {
          for (ServiceRequest serviceRequest : services) {
              com.csidigital.dao.entity.Service service = modelMapper.map(serviceRequest, com.csidigital.dao.entity.Service.class);
              service.setCatalog(catalog);
              serviceRepository.save(service); // Save each service individually
          }
      }

      Catalog savedCatalog = catalogRepository.save(catalog);
      return modelMapper.map(savedCatalog, CatalogResponse.class);
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
        List<com.csidigital.dao.entity.Service> services = existingCatalog.getServices();
        for (Profile profile : profiles) {
            System.out.println("HEEEEEEEEEEEEEEEEEEEEYYYYY");
            System.out.println(profile);
            profile.setCatalog(existingCatalog);
        }
        for (com.csidigital.dao.entity.Service service : services) {
            service.setCatalog(existingCatalog);
        }
        Catalog catalog = catalogRepository.save(existingCatalog);

        return modelMapper.map(catalog, CatalogResponse.class);
    }

    @Override
    public List<ProfileResponse> getCatalogProfilesById(Long id) {
        Catalog catalog = catalogRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Catalog with id " +id+ " not found"));
        List<Profile> profiles = catalog.getProfiles();
       // List<com.csidigital.dao.entity.Service> services = catalog.getServices();

        List<ProfileResponse> profilesList = new ArrayList<>();
      //  List<ServiceResponse> serviceResponseList = new ArrayList<>();

        for (Profile profile : profiles) {
            ProfileResponse response = modelMapper.map(profile, ProfileResponse.class);
            profilesList.add(response);
        }

       /* for (com.csidigital.dao.entity.Service service : services) {
            ServiceResponse response = modelMapper.map(service, ServiceResponse.class);
            serviceResponseList.add(response);
        }*/
        return profilesList;
    }

    @Override
    public List<ServiceResponse> getCatalogServicesById(Long id) {
        Catalog catalog = catalogRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Catalog with id " +id+ " not found"));
        List<com.csidigital.dao.entity.Service> services = catalog.getServices();

        List<ServiceResponse> serviceResponseList = new ArrayList<>();

        for (com.csidigital.dao.entity.Service service : services) {
            ServiceResponse response = modelMapper.map(service, ServiceResponse.class);
            serviceResponseList.add(response);
        }
        return serviceResponseList;
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
