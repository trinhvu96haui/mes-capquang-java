package com.techie.microservices.menu.dto.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.techie.microservices.menu.Enums.CheckListType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateCheckListDto {

    @NotNull
    @Size(max = 200, message = "Maximum length for Check list Name is 200 characters.")
    private String name;

    private BigDecimal minValue;

    private BigDecimal maxValue;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private CheckListType type;

    @NotNull
    private String updatedBy;

}

