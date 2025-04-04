package com.techie.microservices.menu.dto.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessDto {
    private Long id;
    private Integer processId;
    private String value;
    private String name;
    private String active;
    private Integer formLoadId;
    private Integer processVmmsId;
}

