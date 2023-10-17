package com.csidigital.management.controller;

import com.csidigital.dao.entity.Employee;
import com.csidigital.management.service.impl.ResourceImpl;
import com.csidigital.shared.dto.request.ResourceRequest;
import com.csidigital.shared.dto.response.ResourceResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@Transactional
@RequestMapping("/rh/resource")
public class ResourceController {
    @Autowired
    private ResourceImpl resource;

    @GetMapping("/getAll")
    public List<ResourceResponse> getAllResources(){
        return resource.getAllResources();
    }
    @GetMapping("/chefs")
    public List<Employee> getChefs(){
        return resource.getChefs();
    }
    @GetMapping("/Nochefs")
    public List<Employee> getNoChefs(){
        return resource.getNoChefs();
    }
    @GetMapping("/getResource/{id}")
    public ResourceResponse  getResourceById(@PathVariable Long id){
        return resource.getResourceById(id);
    }

    @PostMapping("/addResource")
    public ResourceResponse createResource(@Valid @RequestBody ResourceRequest resourceRequest){
        return resource.createResource(resourceRequest);
    }

    @PutMapping("/updateResource/{id}")
    public ResourceResponse updateResource(@Valid @RequestBody ResourceRequest resourceRequest,
                                                                   @PathVariable Long id){
        return resource.updateResource(resourceRequest, id);
    }

    @DeleteMapping("/deleteResource/{id}")
    public void deleteResource(@PathVariable Long id){
        resource.deleteResource(id);
    }



}
