package com.csidigital.management.controller;


import com.csidigital.management.service.impl.AssEquipmentEmployeeImpl;
import com.csidigital.shared.dto.request.AssEquipmentEmployeeRequest;
import com.csidigital.shared.dto.response.AssEquipmentEmployeeResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rh/assEquipmentEmployee")
public class AssEquipmentEmployeeController {

    @Autowired
    private AssEquipmentEmployeeImpl assEquipmentEmployeeImpl;

    @GetMapping("/getAll")
    public List<AssEquipmentEmployeeResponse> getAllAssEquipmentEmployee() {

        return assEquipmentEmployeeImpl.getAllAssEquipmentEmployee();
    }

    @GetMapping("/get/{id}")
    public AssEquipmentEmployeeResponse getAssEquipmentEmployeeById(@PathVariable Long id){
        return assEquipmentEmployeeImpl.getAssEquipmentEmployeeById(id);
    }

    @PostMapping("/add")
    public AssEquipmentEmployeeResponse createAssEquipmentEmployee(@Valid @RequestBody AssEquipmentEmployeeRequest assEquipmentEmployeeRequest){
        return assEquipmentEmployeeImpl.createAssEquipmentEmployee(assEquipmentEmployeeRequest);
    }

    @PutMapping("/update/{id}")
    public AssEquipmentEmployeeResponse updateAssEquipmentEmployee(@Valid @RequestBody AssEquipmentEmployeeRequest assEquipmentEmployeeRequest,
                                         @PathVariable Long id){
        return assEquipmentEmployeeImpl.updateAssEquipmentEmployee(assEquipmentEmployeeRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAssEquipmentEmployee(@PathVariable Long id){
        assEquipmentEmployeeImpl.deleteAssEquipmentEmployee(id);
    }

}
