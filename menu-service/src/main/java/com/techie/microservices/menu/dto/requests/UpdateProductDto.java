package com.techie.microservices.menu.dto.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductDto {
    private String updatedBy;

    @NotNull
    @Max(200)
    private String name;

    @NotNull
    private Integer modelId;

    @NotNull
    private Integer uomId;

    private String active;
    private String type;
}

