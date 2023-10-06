package com.csidigital.shared.dto.request;

import lombok.Data;


@Data
public class ArticleClientRequest {



    private Integer articleNumber;

    private String articleTitle;

    private String description;
    private Long contractId;
}
