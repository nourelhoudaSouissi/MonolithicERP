package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.*;
import com.csidigital.dao.repository.*;
import com.csidigital.management.service.EvaluationService;
import com.csidigital.shared.dto.request.EvaluationRequest;
import com.csidigital.shared.dto.response.*;
import com.csidigital.shared.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
@AllArgsConstructor
public class EvaluationImpl implements EvaluationService {
    @Autowired
    private EvaluationRepository evaluationRepository ;
    @Autowired
    private ModelMapper modelMapper ;
    @Autowired
    private AssOfferCandidateRepository offerCandidateRepository;

    @Autowired
    private AdministrativeDataRepository administrativeDataRepository;

    @Autowired
    private EmployeeRepository employeeRepository;





   @Override
    public void calculateGlobalAppreciation(Long evaluationId) {
       Evaluation evaluation = evaluationRepository.findById(evaluationId)
               .orElseThrow(() -> new EntityNotFoundException("Evaluation not found"));

       List<Interview> interviews = evaluation.getInterviews();

       double totalMarksSum = 0;
       double coefficientsSum = 0;

       for (Interview interview : interviews) {
           totalMarksSum += interview.getInterviewMark();
           coefficientsSum += interview.getCoefficient();
       }

       if (coefficientsSum > 0) {
           double globalInterviewMark = totalMarksSum / coefficientsSum;

           // Utiliser DecimalFormat pour formater le résultat avec deux chiffres après le point décimal
           DecimalFormatSymbols symbols = new DecimalFormatSymbols();
           symbols.setDecimalSeparator('.');
           DecimalFormat decimalFormat = new DecimalFormat("#.##", symbols);
           String formattedGlobalInterviewMark = decimalFormat.format(globalInterviewMark);

           evaluation.setGlobalAppreciation(Double.parseDouble(formattedGlobalInterviewMark));

           evaluationRepository.save(evaluation);

        }
    }






/*
    @Override
    public void calculateGlobalAppreciation(Long evaluationId) {
        Evaluation evaluation = evaluationRepository.findById(evaluationId)
                .orElseThrow(() -> new EntityNotFoundException("Evaluation not found"));

        List<Interview> interviews = evaluation.getInterviews();

        double totalMarks = 0;
        int questionCount = 0;

        for (Interview interview : interviews) {
            List<UpdatedQuestion> updatedQuestions = interview.getUpdatedQuestions();
            for (UpdatedQuestion question : updatedQuestions) {
                if ( question.getMark() != null){
                    totalMarks += question.getMark();
                    questionCount++;
                }

            }
        }

        if (questionCount > 0) {
            double meanMark = totalMarks / questionCount;
            double maxPossibleMark = 5.0;
            double percentage = (meanMark / maxPossibleMark) * 100.0;

            // Format the percentage value to two decimal places
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
            DecimalFormat decimalFormat = new DecimalFormat("#.##", symbols);
            String formattedPercentage = decimalFormat.format(percentage);

            // Replace the comma with a period in the formatted percentage
            formattedPercentage = formattedPercentage.replace(",", ".");

            evaluation.setGlobalAppreciation(Double.parseDouble(decimalFormat.format(meanMark)));

            evaluationRepository.save(evaluation);
        }
    }*/



    @Override
    public EvaluationResponse createEvaluation(EvaluationRequest request) {
        Employee employee = employeeRepository.findById(request.getEmployeeNum()).orElseThrow();
        Evaluation evaluation = modelMapper.map(request, Evaluation.class);

        LocalDate currentDate = LocalDate.now(); // Get the current date

        evaluation.setEmployee(employee);
        evaluation.setEvaluationDate(currentDate); // Set the evaluation date to the current date

        Evaluation evaluationSaved = evaluationRepository.save(evaluation);
        return modelMapper.map(evaluationSaved, EvaluationResponse.class);
    }

    @Override
    public List<EvaluationResponse> getAllEvaluations() {
        List<Evaluation> evaluations = evaluationRepository.findAll();
        List<EvaluationResponse> evaluationList = new ArrayList<>();

        for (Evaluation evaluation : evaluations) {
            EvaluationResponse response = modelMapper.map(evaluation, EvaluationResponse.class);
            evaluationList.add(response);
        }

        return evaluationList;
    }

    @Override
    public EvaluationResponse getEvaluationById(Long id) {
        Evaluation evaluation = evaluationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Evaluation with id " +id+ " not found"));
        EvaluationResponse evaluationResponse = modelMapper.map(evaluation, EvaluationResponse.class);
        return evaluationResponse;
    }

    @Override
    public EmployeeResponse getEmployeeByEvaluationId(Long id) {
        Evaluation evaluation = evaluationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evaluation with id " + id + " not found"));
        Employee employee = evaluation.getEmployee();
        EmployeeResponse employeeResponse=modelMapper.map(employee, EmployeeResponse.class);
        return employeeResponse;
    }


    @Override
    public List<InterviewResponse> getEvaluationInterviews(Long id) {
        Evaluation evaluation = evaluationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Evaluation with id " +id+ " not found"));
        List<Interview> interviews = evaluation.getInterviews();
        List<InterviewResponse> interviewResponseList = new ArrayList<>();

        for (Interview interview : interviews) {
            InterviewResponse response = modelMapper.map(interview, InterviewResponse.class);
            interviewResponseList.add(response);
        }
        return interviewResponseList ;
    }


    @Override
    public EvaluationResponse updateEvaluation(EvaluationRequest request, Long id) {
        Evaluation existingEvaluation = evaluationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Evaluation with id: " + id + " not found"));
        modelMapper.map(request, existingEvaluation);
        Evaluation savedEvaluation = evaluationRepository.save(existingEvaluation);
        return modelMapper.map(savedEvaluation, EvaluationResponse.class);
    }

    @Override
    public void deleteEvaluation(Long id) {
        evaluationRepository.deleteById(id);
    }


   /* @Override
    public EvaluationResponse calculateGlobalAppreciation(EvaluationRequest evaluation) {
        List<Interview> interviews = evaluation.getInterviews();
        if (interviews != null && !interviews.isEmpty()) {
            int sum = 0;
            int count = 0;
            for (Interview interview : interviews) {
                if (interview.getGlobalMark() != null) {
                    sum += interview.getGlobalMark();
                    count++;
                }
            }
            if (count > 0) {
                int average = sum / count;
                evaluation.setGlobalAppreciation(average);
            } else {
                evaluation.setGlobalAppreciation(0);
            }
        } else {
            evaluation.setGlobalAppreciation(0);
        }

        Evaluation savedEvaluation = evaluationRepository.save(evaluation);
        return modelMapper.map(savedEvaluation, EvaluationResponse.class);
    }

    // Rest of the service methods...
}*/

}