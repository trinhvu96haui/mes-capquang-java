package com.techie.microservices.menu.dto.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelDto {
    private Integer id;
    private Integer modelId;
    private String value;
    private String name;
    private Integer categoryId;
    private String active;
}

