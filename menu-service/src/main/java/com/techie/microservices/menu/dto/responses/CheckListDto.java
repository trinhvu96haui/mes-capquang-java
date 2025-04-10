package com.techie.microservices.menu.dto.responses;

import com.techie.microservices.menu.Enums.CheckListType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CheckListDto {
    private Long id;
    private Integer checkListId;
    private String value;
    private String name;
    private BigDecimal minValue;
    private BigDecimal maxValue;
    private CheckListType type;
}

