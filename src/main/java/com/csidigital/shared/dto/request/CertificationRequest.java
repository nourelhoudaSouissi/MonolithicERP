package com.csidigital.shared.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data

public class CertificationRequest {
    private LocalDate certificationObtainedDate;
    private String certificationTitle;

    private Long technicalFileNum ;

}
