package com.techie.microservices.menu.dto.responses;

import com.techie.microservices.menu.Enums.InputMethod;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProcessDto {
    private Long id;
    private Integer processId;
    private String value;
    private String name;
    private String status;
    private InputMethod inputMethod;
    private List<CheckListDto> checkLists;
}

