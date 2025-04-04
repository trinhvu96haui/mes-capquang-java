package com.techie.microservices.menu.dto.responses;

import com.techie.microservices.menu.Enums.GroupProcessType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupProcessDto {
    private Long id;
    private String code;
    private String name;
    private String description;
    private String version;
    private String linkDocument;
    private GroupProcessType type;
}

