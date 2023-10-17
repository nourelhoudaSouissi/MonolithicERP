package com.csidigital.management.service;



import com.csidigital.shared.dto.request.TaskDtoRequest;
import com.csidigital.shared.dto.response.TaskDtoResponse;

import java.util.List;

public interface TaskService {
    // Get All tasks
    List<TaskDtoResponse> getAllTasks();

    // Get task by id
    TaskDtoResponse getTaskById(Long id);

    // Add new task
    TaskDtoResponse createTask(TaskDtoRequest taskDtoRequest);


    // Update task by id

    TaskDtoResponse updateTask(Long id,TaskDtoRequest taskDtoRequest);


    //delete task by id
    void deleteTaskById(Long id);
}
