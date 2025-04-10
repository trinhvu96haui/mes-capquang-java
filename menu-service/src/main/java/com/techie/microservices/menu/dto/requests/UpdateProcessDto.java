package com.techie.microservices.menu.dto.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.techie.microservices.menu.Enums.InputMethod;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateProcessDto {
    private String updatedBy;

    @NotNull
    @Size(max = 200, message = "Maximum length for Process Name is 200 characters.")
    private String name;

    @NotNull
    private boolean status;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private InputMethod inputMethod;

    private List<Integer> checkListIds;
}

