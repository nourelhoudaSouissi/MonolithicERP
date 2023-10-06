package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.Equipment;
import com.csidigital.dao.repository.EmployeeRepository;
import com.csidigital.dao.repository.EquipmentRepository;
import com.csidigital.management.service.EquipmentService;
import com.csidigital.shared.dto.request.EquipmentRequest;
import com.csidigital.shared.dto.response.EquipmentResponse;
import com.csidigital.shared.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class EquipmentImpl implements EquipmentService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EquipmentResponse createEquipment(EquipmentRequest request) {
        Equipment equipment = modelMapper.map(request, Equipment.class);
        Equipment equipmentSaved = equipmentRepository.save(equipment);

        // Génération de la référence
        String reference = "Eq";
        Long equipmentCount = equipmentRepository.countEquipment();
        String equipmentSuffix = String.format("%04d", equipmentCount);

        String finalReference = reference + "_" + equipmentSuffix;
        equipmentSaved.setReference(finalReference);

        return modelMapper.map(equipmentSaved, EquipmentResponse.class);
    }




    @Override
    public List<EquipmentResponse> getAllEquipments() {
        List<Equipment> equipment = equipmentRepository.findAll();
        List<EquipmentResponse> equipmentList = new ArrayList<>();


        for (Equipment equipments : equipment) {
            EquipmentResponse response = modelMapper.map(equipments, EquipmentResponse.class);
            //response.setEmployeeFN(equipments.getEmployee().getFirstName());
            // response.setEmployeeLN(equipments.getEmployee().getLastName());
            equipmentList.add(response);
        }

        return equipmentList;
    }

    @Override
    public EquipmentResponse getEquipmentById(Long id) {
        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Availability with id " + id + " not found"));
        EquipmentResponse equipmentResponse = modelMapper.map(equipment, EquipmentResponse.class);

        return equipmentResponse;
    }

    @Override
    public EquipmentResponse updateEquipment(EquipmentRequest request, Long id) {
        Equipment existingEquipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Availability with id: " + id + " not found"));
        modelMapper.map(request, existingEquipment);
        Equipment savedEquipment = equipmentRepository.save(existingEquipment);
        return modelMapper.map(savedEquipment, EquipmentResponse.class);
    }

    @Override
    public void deleteEquipment(Long id) {
        equipmentRepository.deleteById(id);

    }

    @Override
    public void updateStatusToAvailableById(Long id) {
        equipmentRepository.updateStatusToAvailableById(id);
    }

    @Override
    public void updateStatusToUnavailableById(Long id) {
        equipmentRepository.updateStatusToUnavailableById(id);

    }


    @Override
    public void updateMotifById(Long id, String motifUnavailability, LocalDate disponibilityDate) {
     Equipment equipment = equipmentRepository.findById(id).orElseThrow();
     equipment.setMotifUnavailability(motifUnavailability);
     equipment.setDisponibilityDate(disponibilityDate);
     equipmentRepository.save(equipment);
    }


  @Override
    public void updateAffectedById(Long id) {
        equipmentRepository.updateAffectedById(id);
    }



    @Override
    public void updateUnaffectedById(Long id) {
        equipmentRepository.updateUnaffectedById(id);

    }

    @Override
    public void updateRestituationById(Long id, String returnComment, String returnStatus, LocalDate returnDate) {
        Equipment equipment = equipmentRepository.findById(id).orElseThrow();
        equipment.setReturnComment(returnComment);
        equipment.setReturnStatus(returnStatus);
        equipment.setReturnDate(returnDate);
        equipmentRepository.save(equipment);
    }

    @Override
    public int countNonAffectables() {
        return equipmentRepository.countNonAffectables();
    }

    @Override
    public int countAffectables() {
        return equipmentRepository.countAffectables();
    }

    @Override
    public int countNonAmortizables() {
        return equipmentRepository.countNonAmortizables();
    }

    @Override
    public int countAmortizables() {
        return equipmentRepository.countAmortizables();
    }

    @Override
    public int countAvailable() {
        return equipmentRepository.countAvailalbe();
    }

    @Override
    public int countUnavailable() {
        return equipmentRepository.countUnavailalbe();
    }


    @Override
    public int countAffectation() {
        return equipmentRepository.countAffectation();
    }

    @Override
    public int countUnaffectation() {
        return equipmentRepository.countUnaffectation();
    }

    @Override
    public int countAvailalbeAff() {
        return equipmentRepository.countAvailalbeAff();
    }

    @Override
    public int countUnavailalbeAff() {
        return equipmentRepository.countUnavailalbeAff();
    }

    @Override
    public List<Equipment>  getAffectableEquipments() {

        return equipmentRepository.getAffectableEquipments();
    }

    @Override
    public Long countEquipment() {
        return equipmentRepository.countEquipment();
    }







}