package com.techie.microservices.menu.dto.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.techie.microservices.menu.Enums.GroupProcessType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateGroupProcessDto {
    @NotNull
    private String name;
    private String description;
    private String version;
    private String linkDocument;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private GroupProcessType type;
    private String updatedBy;

}

