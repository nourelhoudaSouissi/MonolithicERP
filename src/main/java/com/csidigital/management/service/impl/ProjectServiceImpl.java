package com.csidigital.management.service.impl;


import com.csidigital.dao.entity.*;
import com.csidigital.dao.repository.*;
import com.csidigital.management.service.ProjectService;
import com.csidigital.shared.dto.request.ProjectDtoRequest;
import com.csidigital.shared.dto.response.ProjectDtoResponse;
import com.csidigital.shared.dto.response.SousTacheResponse;
import com.csidigital.shared.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private  ProjectRepository projectRepository;
    @Autowired
    private ResourceRepository resourceRepository ;

    @Autowired
    private EmployeeRepository employeeRepository ;
    @Autowired
    private PhaseRepository phaseRepository ;
    @Autowired
    private ResponsableExternRepository responsableExternRepository ;
    @Autowired
    private ProjectReferenceSequenceRepository sequenceRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectReferenceSequenceRepository sequenceRepository) {
        this.projectRepository = projectRepository;
        this.sequenceRepository = sequenceRepository;}
    @Override
    public List<ProjectDtoResponse> getAllProjects() {

        List<Project> projects = projectRepository.findAll();
        List<ProjectDtoResponse> projectList = new ArrayList<>();

        for (Project project : projects) {
            ProjectDtoResponse projectDtoResponse = modelMapper.map(project, ProjectDtoResponse.class);
            projectList.add(projectDtoResponse);
        }

        return projectList;
    }

    @Override
    public ProjectDtoResponse getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Project with id " +id+ " not found"));
        ProjectDtoResponse projectDtoResponse = modelMapper.map(project, ProjectDtoResponse.class);
        return projectDtoResponse;

    }
    public List<SousTacheResponse> getTasksById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Project with id " +id+ " not found"));
        List<Phase> phases = project.getPhases();
        List<Task> tasks = new ArrayList<>();
        List<SubTask> subTasks = new ArrayList<>();
        List<SousTacheResponse> sousTacheResponses = new ArrayList<>();
        for (Phase phase : phases) {
            List<Task> phaseTasks = phase.getTasks();
            tasks.addAll(phaseTasks);
        }
        for(Task t : tasks){
            subTasks.addAll(t.getSubTaskList());
        }
        for (SubTask s : subTasks) {
            SousTacheResponse sousTacheResponse = modelMapper.map(s , SousTacheResponse.class);
            sousTacheResponses.add(sousTacheResponse);
        }
        return sousTacheResponses;
    }

    public List<Phase> getProjectPhase(Long id){
        Project project = projectRepository.findById(id).orElseThrow();
        return project.getPhases();
    }
    public List<Task> getProjectTask(Long id){
        Project project = projectRepository.findById(id).orElseThrow();
        List<Phase> phaseList = project.getPhases();

        List<Task> tasks = new ArrayList<>();
        for (Phase p : phaseList){
            tasks.addAll(p.getTasks());
        }
    return tasks;
    }

    @Override
    public List<Employee> getProjectResource(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Project with id " +id+ " not found"));

        return project.getEmployees();

    }
    public List<ResponsableExtern> getProjectResp(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Project with id " +id+ " not found"));

        return project.getResponsables();

    }
    @Override
    public ProjectDtoResponse createProject(ProjectDtoRequest projectDtoRequest) {
        List<ResponsableExtern> responsableExterns = projectDtoRequest.getResponsables();
        ProjectReferenceSequence sequence = sequenceRepository.findById(1L)
                .orElse(null) ;



        if (sequence == null) {
            sequence = new ProjectReferenceSequence();
            sequence = sequenceRepository.save(sequence);
        }
        Project project = modelMapper.map(projectDtoRequest, Project.class);
        String projectReference = String.format("PR_%04d", sequence.getNextValue());
        project.setProjectReference(projectReference);
       /* Order order = orderRepository.findById(projectDtoRequest.getOrderNum())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));;
        project.setOrder(order);*/
        Order order = null;
        if(projectDtoRequest.getOrderNum()!=null) {
            order = orderRepository.findById(projectDtoRequest.getOrderNum())
                    .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        }
        project.setOrder(order);

        List<Employee> existingResources = employeeRepository.findAllById(projectDtoRequest.getEmployeeIds());
        for(Employee res : existingResources) {
            res.getProject().add(project);
            //resourceRepository.save(res);
        }

            if (responsableExterns != null) {
                for (ResponsableExtern responsableExtern : responsableExterns) {
                    responsableExtern.setProject(project);

                    responsableExternRepository.save(responsableExtern);
                }
            }

        project.setEmployees(existingResources);
        project = projectRepository.save(project);
        employeeRepository.saveAll(existingResources);
        sequence.incrementNextValue();
        sequenceRepository.save(sequence);
        Project ProjectSaved = projectRepository.save(project);
        return modelMapper.map(ProjectSaved, ProjectDtoResponse.class);
    }

    public void addResourceToProject(Long projectId, List<Long> employeeIds) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project not found"));
        List<Employee> employees = employeeRepository.findAllById(employeeIds);
        for(Employee res : employees){
            res.getProject().add(project);
            project.getEmployees().add(res);
        }
        // Add the resource to the project's resource list


        // Save the updated project
        projectRepository.save(project);
    }

    @Override
    public ProjectDtoResponse updateProject(Long id, ProjectDtoRequest projectDtoRequest) {
        Employee responsable =null;
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project with id: " + id + " not found"));

        modelMapper.map(projectDtoRequest, project);

        List<Employee> existingResources = new ArrayList<>();

        for (Long resourceId : projectDtoRequest.getEmployeeIds()) {
            Employee employee = employeeRepository.findById(resourceId)
                    .orElseThrow(() -> new ResourceNotFoundException("Resource with id: " + resourceId + " not found"));
            existingResources.add(employee);
        }

        // Save new resources if necessary
        for (Employee employee : existingResources) {
            if (employee.getId() == null) {
                employee = employeeRepository.save(employee);
            }
            employee.getProject().add(project);
        }

        // Save responsible resource if necessary

        responsable.getProject().add(project);
        employeeRepository.save(responsable);
        project.setEmployees(existingResources);


        Project updatedProject = projectRepository.save(project);
        return modelMapper.map(updatedProject, ProjectDtoResponse.class);
    }

    /*public ProjectDtoResponse updateProject(Long id, ProjectDtoRequest projectDtoRequest) {
        Project project = projectRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Project with id: " + id + " not found"));
        modelMapper.map(projectDtoRequest, project);
        List<Resource> existingResources = resourceRepository.findAllById(projectDtoRequest.getResourceIds());

        for(Resource res : existingResources) {
            res.setProject(project);
        }
        Resource responsable = resourceRepository.findById(projectDtoRequest.getResponsableNum()).orElseThrow();
        responsable.setProject(project);
        Resource resp= resourceRepository.save(responsable);
        project.setResources(existingResources);
        project.setResponsable(resp);

        Project updatedProject = projectRepository.save(project);
        return modelMapper.map(updatedProject, ProjectDtoResponse.class);
    }*/

    @Override
    public void deleteProjectById(Long id) {
        Project project = projectRepository.findById(id).orElse(null);

        List<Employee> employees = project.getEmployees();
        List<Phase> phases = project.getPhases();
        project.setPhases(null);
        for(Employee r : employees) {
            r.getProject().remove(project);
            r.setSubTasks(null);

            employeeRepository.save(r);
        }
        for(Phase p : phases){
            p.setProject(null);
            phaseRepository.save(p);
        }
            projectRepository.deleteById(id);

    }

}



