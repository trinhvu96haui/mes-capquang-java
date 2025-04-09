package com.techie.microservices.menu.dto.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateFormLoadDto {
    @NotNull
    private Integer formLoadId;
    private String createdBy;
    private String name;
    private String path;
    private String status;
    private String note;
}

