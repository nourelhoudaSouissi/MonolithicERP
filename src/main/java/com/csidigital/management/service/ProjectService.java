package com.csidigital.management.service;


import com.csidigital.dao.entity.Resource;
import com.csidigital.shared.dto.request.ProjectDtoRequest;
import com.csidigital.shared.dto.response.ProjectDtoResponse;

import java.util.List;

public interface ProjectService {


    // Get All projects
    List<ProjectDtoResponse> getAllProjects();

    // Get  by project id
    ProjectDtoResponse getProjectById(Long id);

    // Add new project
    ProjectDtoResponse createProject(ProjectDtoRequest projectDtoRequest);


    // Update project by id

    ProjectDtoResponse updateProject(Long id, ProjectDtoRequest projectDtoRequest);

     List<Resource> getProjectResource(Long id);
    //delete project by id
    void deleteProjectById(Long id);
}
