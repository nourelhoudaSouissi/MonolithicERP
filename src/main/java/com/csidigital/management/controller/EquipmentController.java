package com.csidigital.management.controller;


import com.csidigital.dao.entity.Equipment;
import com.csidigital.management.service.impl.EquipmentImpl;
import com.csidigital.shared.dto.request.EquipmentRequest;
import com.csidigital.shared.dto.response.EquipmentResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rh/equipment")
public class EquipmentController {
    @Autowired
    private EquipmentImpl equipmentImpl;
    @GetMapping("getAll")
    public List<EquipmentResponse> getAllEquipment() {

        return equipmentImpl.getAllEquipments();
    }

    @GetMapping("/getById/{id}")
    public EquipmentResponse getEquipmentById(@PathVariable Long id){
        return equipmentImpl.getEquipmentById(id);
    }

    @PostMapping("/add")
    public EquipmentResponse createEquipment(@Valid @RequestBody EquipmentRequest equipmentRequest){
        return equipmentImpl.createEquipment(equipmentRequest);
    }

    @PutMapping("/update/{id}")
    public EquipmentResponse updateEquipment(@Valid @RequestBody EquipmentRequest equipmentRequest,
                                                   @PathVariable Long id){
        return equipmentImpl.updateEquipment(equipmentRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEquipement(@PathVariable Long id){

        equipmentImpl.deleteEquipment(id);}

    @PutMapping("/updateToAvailableById/{id}")
    void  updateStatusToAvailableById(@PathVariable Long id){

        equipmentImpl.updateStatusToAvailableById(id);
    }

    @PutMapping("/updateToUnavailableById/{id}")
    void  updateStatusToUnavailableById(@PathVariable Long id){

        equipmentImpl.updateStatusToUnavailableById(id);
    }

    @PutMapping("/updateAffectedById/{id}")
    void  updateAffectedById(@PathVariable Long id){

        equipmentImpl.updateAffectedById(id);
    }
    @PutMapping("/updateUnaffectedById/{id}")
    void  updateUnaffectedById(@PathVariable Long id){

        equipmentImpl.updateUnaffectedById(id);
    }
    @PutMapping("/updateMotifById/{id}")
    void updateMotifById(@PathVariable Long id, @RequestBody EquipmentRequest request) {
        String motifUnavailability = request.getMotifUnavailability();
        LocalDate disponibilityDate = request.getDisponibilityDate();
        equipmentImpl.updateMotifById(id, motifUnavailability,disponibilityDate);
    }

    @PutMapping("/updateRestituationById/{id}")
    void updateRestituationById(@PathVariable Long id, @RequestBody EquipmentRequest request) {
        String returnComment = request.getReturnComment();
        String returnStatus = request.getReturnStatus();
        LocalDate returnDate = request.getReturnDate();
        equipmentImpl.updateRestituationById(id,returnComment,returnStatus,returnDate);
    }
    @GetMapping("/getAllAffectable")
    public List<Equipment> getAllAffectables() {

        return equipmentImpl.getAffectableEquipments();
    }

    @GetMapping("/getNumber")
    public Long countEquipment() {
        return equipmentImpl.countEquipment();
    }

    @GetMapping("/countAmortizables")
    public int countAmortizables() {
        return equipmentImpl.countAmortizables();
    }
    @GetMapping("/countNonAmortizables")
    public int countNonAmortizables() {
        return equipmentImpl.countNonAmortizables();
    }
    @GetMapping("/countAffectables")
    public int countAffectables() {
        return equipmentImpl.countAffectables();
    }
    @GetMapping("/countNonAffectables")
    public int countNonAffectables() {
        return equipmentImpl.countNonAffectables();
    }
    @GetMapping("/countAvailable")
    public int countAvailable() {

        return equipmentImpl.countAvailable();
    }
    @GetMapping("/countUnavailable")
    public int countUnavailable() {

        return equipmentImpl.countUnavailable();
    }
    @GetMapping("/countAffectation")
    public int countAffectation() {

        return equipmentImpl.countAffectation();
    }
    @GetMapping("/countUnaffectation")
    public int countUnaffectation() {

        return equipmentImpl.countUnaffectation();
    }
    @GetMapping("/countAvailableAff")
    public int countAvailableAff() {

        return equipmentImpl.countAvailalbeAff();
    }
    @GetMapping("/countUnavailableAff")
    public int countUnavailableAff() {

        return equipmentImpl.countUnavailalbeAff();
    }
}
