package com.csidigital.management.controller;

import com.csidigital.management.service.impl.CatalogServiceImpl;
import com.csidigital.shared.dto.request.CatalogRequest;
import com.csidigital.shared.dto.response.CatalogResponse;
import com.csidigital.shared.dto.response.ProfileResponse;
import com.csidigital.shared.dto.response.ServiceResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crm/catalogs")
//@CrossOrigin(origins = "${cross.origin.url}")
public class CatalogController {
    @Autowired
    private CatalogServiceImpl catalogService ;

    @GetMapping()
    public List<CatalogResponse> getAllCatalogs() {
        return catalogService.getAllCatalogs();
    }

    @GetMapping("/{id}")
    public CatalogResponse getCatalogById(@PathVariable Long id){
        return catalogService.getCatalogById(id);
    }

    @GetMapping("/{id}/profiles")
    public List<ProfileResponse> getCatalogProfilesById(@PathVariable Long id){
        return catalogService.getCatalogProfilesById(id);
    }

    @GetMapping("/{id}/services")
    public List<ServiceResponse> getCatalogServicesById(@PathVariable Long id){
        return catalogService.getCatalogServicesById(id);
    }

    @GetMapping("/catalogByProfileId/{profileId}")
    public CatalogResponse getCatalogByProfileId(@PathVariable Long profileId){
        return catalogService.getCatalogByProfileId(profileId);
    }

    @PostMapping()
    public CatalogResponse createCatalog(@RequestBody CatalogRequest catalogRequest ){
        System.out.println(catalogRequest.getProfiles());
        return catalogService.createCatalog(catalogRequest );
    }

    @PutMapping("/{id}")
    public CatalogResponse updateCatalog(@Valid @RequestBody CatalogRequest catalogRequest,
                                                 @PathVariable Long id){
        return catalogService.updateCatalog(catalogRequest, id);
    }

    @DeleteMapping("/{id}")
    public void deleteCatalog(@PathVariable Long id){
        catalogService.deleteCatalog(id);
    }
}
