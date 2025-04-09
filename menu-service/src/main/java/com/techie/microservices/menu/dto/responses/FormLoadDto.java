package com.techie.microservices.menu.dto.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormLoadDto {
    private Long id;
    private Integer formLoadId;
    private String name;
    private String path;
    private String status;
    private String note;
}

