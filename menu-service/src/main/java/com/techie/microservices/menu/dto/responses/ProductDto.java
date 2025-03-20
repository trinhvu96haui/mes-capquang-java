package com.techie.microservices.menu.dto.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Long id;
    private Integer productId;
    private Integer modelId;
    private String value;
    private String name;
    private String cartonName;
    private String active;
    private Boolean isAuto;
    private Boolean isYear;
    private Boolean isMonth;
    private Boolean isDay;
    private Boolean isRepair;
    private Boolean isReject;
    private String type;
}

