package com.csidigital.management.controller;

import com.csidigital.management.service.impl.SubTaskImp;
import com.csidigital.shared.dto.request.SousTacheRequest;
import com.csidigital.shared.dto.response.SousTacheResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subTask")
public class SubClassController {
    @Autowired
    private SubTaskImp subTaskService;
    @GetMapping("/getAll")
    public List<SousTacheResponse> getAllTasks() {
        return subTaskService.getAllTasks();
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<SousTacheResponse> getTaskById(@PathVariable Long id) {
        return new ResponseEntity<>(subTaskService.getTaskById(id), HttpStatus.OK);
    }
    @PostMapping("/add")

    public SousTacheResponse createTask(@Valid @RequestBody SousTacheRequest taskDtoRequest) {
        return subTaskService.createTask(taskDtoRequest);
    }
    @PutMapping("/update/{id}")
    public SousTacheResponse updateSousTache(@Valid @RequestBody SousTacheRequest sousTacheRequest , @PathVariable Long id){
        return subTaskService.updateTask(sousTacheRequest , id);
}
    @DeleteMapping("/delete")

    public void deleteTask(@PathVariable Long id) {
         subTaskService.deleteSubTask(id);
    }
}
