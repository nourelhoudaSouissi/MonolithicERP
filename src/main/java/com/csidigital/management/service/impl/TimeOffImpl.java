package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.*;
import com.csidigital.dao.repository.*;;
import com.csidigital.management.service.*;
import com.csidigital.shared.dto.request.TimeOffRequest;
import com.csidigital.shared.dto.response.*;
import com.csidigital.shared.enumeration.RequestStatus;
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
public class TimeOffImpl implements TimeOffService {
    @Autowired
    private TimeOffRepository timeOffRepository ;

    @Autowired
    private LeaveTypeRepository leaveTypeRepository ;
    @Autowired
    private EmployeeRepository employeeRepository ;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EmailImpl emailService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private LeaveTypeService leaveTypeService;


    @Override
    public TimeOffResponse createTimeOff(TimeOffRequest request) {

        LeaveType leaveType = null;
        Employee employee = null;
        if(request.getLeaveTypeNum()!=null) {
            leaveType = leaveTypeRepository.findById(request.getLeaveTypeNum())
                    .orElseThrow();
        }
        if(request.getEmployeeNum()!=null) {
            employee = employeeRepository.findById(request.getEmployeeNum())
                    .orElseThrow();
        }
        TimeOff timeOff = modelMapper.map(request, TimeOff.class);
        timeOff.setRequestStatus(RequestStatus.PENDING);
        timeOff.setLeaveType(leaveType);
        timeOff.setEmployee(employee);
        timeOff.setRequestInputDate(LocalDate.now());

        TimeOff TimeOffSaved = timeOffRepository.save(timeOff);

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(timeOff.getEmployee().getEmailOne());
        emailDetails.setMsgBody("Email body");
        emailDetails.setMsgBody("Je tiens par la présente à vous informer de mon souhait de prendre"+" " + timeOff.getTimeOffPeriod() + " Jours"+ " pour la période allant du "+ timeOff.getStartDate() +" au" + timeOff.getEndDate() + " " +" inclus.\n Je vous remercie par avance de la bienveillance que vous porterez à ma demande, et vous saurai gré de bien vouloir m’informer de votre décision avant" + timeOff.getLeaveType().getAlertNumberDays()+ " "+"jours.\n Dans cette attente, je vous prie d’agréer,"+ timeOff.getEmployee().getCivility()+ " , l’expression de mes respectueuses salutations.");
        emailDetails.setSubject("Demande de congé"+ " " +timeOff.getLeaveType().getName() );
        //emailDetails.setAttachment("path/to/attachment");

        emailService.sendSimpleMail(emailDetails);

        return modelMapper.map(TimeOffSaved, TimeOffResponse.class);
    }



    @Override
    @Transactional
    public List<TimeOffResponse> getAllTimeOff() {
        List<TimeOff> timeOff = timeOffRepository.findAll();
        List<TimeOffResponse> timeOffList = new ArrayList<>();

        for (TimeOff timeOffGet: timeOff) {
            TimeOffResponse response = modelMapper.map(timeOffGet, TimeOffResponse.class);
            response.setLeaveTypeName(timeOffGet.getLeaveType().getName());
            response.setTimeOffType(timeOffGet.getLeaveType().getTimeOffType());
            response.setLeaveTypeAlertNumberDays(timeOffGet.getLeaveType().getAlertNumberDays());
            response.setEmployeeFirstName(timeOffGet.getEmployee().getFirstName());
            response.setEmployeeLastName(timeOffGet.getEmployee().getLastName());

            timeOffList.add(response);
        }
        return timeOffList;
    }







   @Override
   public TimeOffResponse getTimeOffById(Long id) {
       TimeOff timeOff = timeOffRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("TimeOff with id " + id + " not found"));

       TimeOffResponse timeOffResponse = modelMapper.map(timeOff, TimeOffResponse.class);

       // Retrieve associated Employee and calculate remainingPaidLeave
       EmployeeResponse employeeResponse = employeeService.getEmployeeById(timeOff.getEmployee().getId());

       // Retrieve associated Employee and calculate remainingPaidLeave
       LeaveTypeResponse leaveTypeResponse = leaveTypeService.getLeaveTypeById(timeOff.getLeaveType().getId());

       // Set remainingPaidLeave in the TimeOffResponse
       timeOffResponse.setRemainingPaidLeave(employeeResponse.getRemainingPaidLeave());

       return timeOffResponse;
   }



    @Override
    public TimeOffResponse updateTimeOff(TimeOffRequest request, Long id) {
        TimeOff existingTimeOff = timeOffRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("TimeOff with id: " + id + " not found"));

        modelMapper.map(request, existingTimeOff);
        TimeOff savedTimeOff = timeOffRepository.save(existingTimeOff);
        existingTimeOff.setRequestStatus(RequestStatus.PENDING);
        return modelMapper.map(savedTimeOff, TimeOffResponse.class);
    }

    @Override
    public void deleteTimeOff(Long id) {
        timeOffRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateStatusToValidatedById(Long id) {

        TimeOff timeOff = timeOffRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TimeOff not found with ID: " + id));

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setSender("soumrihoussem6@gmail.com");
        emailDetails.setRecipient(timeOff.getEmployee().getEmailOne());
        emailDetails.setMsgBody("Votre Congé de"+" " + timeOff.getTimeOffPeriod() + " Jours"+ " pour la période allant du "+ timeOff.getStartDate() +" au" + timeOff.getEndDate() + " " +" inclus.\n a été accepté. \n"+ timeOff.getEmployee().getHierarchicalSuperior().getCivility()+" "+ timeOff.getEmployee().getHierarchicalSuperior().getFirstName()+" "+ timeOff.getEmployee().getHierarchicalSuperior().getLastName());
        emailDetails.setSubject("Validation Congés"+ " " +timeOff.getLeaveType().getName() );
        //emailDetails.setAttachment("path/to/attachment");

        emailService.sendSimpleMail(emailDetails);

        timeOffRepository.updateStatusToValidatedById(id);
    }


    @Override
    public void updateStatusToRejectedById(Long id) {
        TimeOff timeOff = timeOffRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TimeOff not found with ID: " + id));

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(timeOff.getEmployee().getEmailOne());
        emailDetails.setSender(timeOff.getEmployee().getHierarchicalSuperior().getEmailOne());
        emailDetails.setMsgBody("Email body");
        emailDetails.setMsgBody("Votre Congé de"+" " + timeOff.getTimeOffPeriod() + " Jours"+ " pour la période allant du "+ timeOff.getStartDate() +" au" + timeOff.getEndDate() + " " +" inclus.\n a été refusé. \n"+ timeOff.getEmployee().getHierarchicalSuperior().getCivility()+" "+ timeOff.getEmployee().getHierarchicalSuperior().getFirstName()+" "+ timeOff.getEmployee().getHierarchicalSuperior().getLastName());
        emailDetails.setSubject("Demande de congé"+ " " +timeOff.getLeaveType().getName() );
        //emailDetails.setAttachment("path/to/attachment");

        emailService.sendSimpleMail(emailDetails);
        timeOffRepository.updateStatusToRejectedById(id);
    }

    @Override
    public List<Object[]> getTotalDurationByLeaveTypeAndEmployeeId(Long employeeId) {
        return timeOffRepository.getTotalDurationByLeaveTypeAndEmployeeId(employeeId);

    }

    @Override
    public List<Object[]> getTotalDurationSpecialPaidLeaveByLeaveTypeAndEmployeeId(Long employeeId) {
        return timeOffRepository.getTotalDurationSpecialPaidLeaveByLeaveTypeAndEmployeeId(employeeId);
    }

    @Override
    public List<Object[]> getTotalDurationSicknessLeaveByLeaveTypeAndEmployeeId(Long employeeId) {
        return timeOffRepository.getTotalDurationSicknessLeaveByLeaveTypeAndEmployeeId(employeeId);
    }

    @Override
    public Double getTotalDurationSpecialPaidLeaveEmployeeId(Long employeeId) {
        return timeOffRepository.getTotalDurationSpecialPaidLeaveEmployeeId(employeeId);
    }

    @Override
    public Double getTotalDurationSicknessLeaveEmployeeId(Long employeeId) {
        return timeOffRepository.getTotalDurationSicknessLeaveEmployeeId(employeeId);
    }

    @Override
    public Double getTotalDurationPaidLeaveEmployeeId(Long employeeId) {
        return timeOffRepository.getTotalDurationPaidLeaveEmployeeId(employeeId);
    }

    @Override
    public Double getTotalDurationUnpaidLeaveEmployeeId(Long employeeId) {
        return timeOffRepository.getTotalDurationUnpaidLeaveEmployeeId(employeeId);
    }

    @Override
    public int countValidated() {
        return timeOffRepository.countValidated();
    }

    @Override
    public int countRejected() {
        return timeOffRepository.countRejected();
    }

    @Override
    public int countPending() {
        return timeOffRepository.countPending();
    }
}
