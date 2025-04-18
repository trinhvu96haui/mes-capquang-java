package com.techie.microservices.menu.dto.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.techie.microservices.menu.Enums.GroupProcessType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateGroupProcessDto {
    @NotNull
    private String code;
    @NotNull
    private String name;
    private String description;
    private String version;
    private String linkDocument;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private GroupProcessType type;
    private String createdBy;
}

