package com.techie.microservices.menu.dto.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateProcessDto {
    @NotNull
    private Integer processId;

    @NotNull
    @Size(max = 100, message = "Maximum length for Process value is 100 characters.")
    private String value;

    private String createdBy;

    @NotNull
    @Size(max = 200, message = "Maximum length for Process Name is 200 characters.")
    private String name;

    private String active;

    @NotNull
    private Integer formLoadId;

    @NotNull
    private Integer processVmmsId;

}

