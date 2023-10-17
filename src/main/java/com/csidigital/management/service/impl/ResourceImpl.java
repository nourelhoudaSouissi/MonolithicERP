package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.*;

import com.csidigital.dao.repository.*;
import com.csidigital.management.service.ResourceService;
import com.csidigital.shared.dto.request.ResourceRequest;
import com.csidigital.shared.dto.response.ResourceResponse;
import com.csidigital.shared.enumeration.Title;
import com.csidigital.shared.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
@Transactional
public class ResourceImpl implements ResourceService {
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private ProjectRepository projectRepository ;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResourceResponse createResource(ResourceRequest request) {
        List<Project> projet = null ;
        Project responsable = null ;
        if (request.getProjectNum()!=null) {
            projet = projectRepository.findAllById(request.getProjectNum());
        }
        if (request.getPrjNum()!=null) {
            responsable = projectRepository.findById(request.getPrjNum()).orElseThrow();
        }
        Resource resource = modelMapper.map(request, Resource.class);
        resource.setProject(projet);

        Resource resourceSaved = resourceRepository.save(resource);
        return modelMapper.map(resourceSaved, ResourceResponse.class);
    }

    @Override
    public List<ResourceResponse> getAllResources() {
        List<Resource> resources = resourceRepository.findAll();
        List<ResourceResponse> resourceResponseList= new ArrayList<>();

        for (Resource resource : resources) {
           ResourceResponse response = modelMapper.map(resource ,ResourceResponse.class);
            resourceResponseList.add(response);
        }

        return resourceResponseList;
    }
    @Override
    public List<Employee> getChefs() {
        List<Employee> employees = employeeRepository.findAll();
        List<Employee> employeeList= new ArrayList<>();

        for (Employee emp : employees) {



                if(emp.getTitle().equals(Title.PROJECT_MANAGER)){
            employeeList.add(emp);}
        }

        return employeeList;
    }
    public List<Employee> getNoChefs() {
        List<Employee> employees = employeeRepository.findAll();
        List<Employee> employeeList= new ArrayList<>();

        for (Employee emp : employees) {



            if (!emp.getTitle().equals(Title.PROJECT_MANAGER)){
                employeeList.add(emp);}
        }

        return employeeList;
    }
    @Override
    public ResourceResponse getResourceById(Long id) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource with id " + id + " not found"));
        ResourceResponse resourceResponse = modelMapper.map(resource, ResourceResponse.class);
        return resourceResponse;
    }

    @Override
    public ResourceResponse updateResource(ResourceRequest request, Long id) {
        Resource existingResource = resourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource with id: " + id + " not found"));
        modelMapper.map(request, existingResource);
        Resource savedResource = resourceRepository.save(existingResource);
        return modelMapper.map(savedResource, ResourceResponse.class);
    }

    @Override
    public void deleteResource(Long id) {resourceRepository.deleteById(id);
    }


}
