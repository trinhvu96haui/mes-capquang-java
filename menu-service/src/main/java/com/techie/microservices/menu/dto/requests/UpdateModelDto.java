package com.techie.microservices.menu.dto.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateModelDto {
    private String updatedBy;

    @Size(max = 200, message = "Maximum length for Product Name is 200 characters.")
    private String name;

    private String active;
}

