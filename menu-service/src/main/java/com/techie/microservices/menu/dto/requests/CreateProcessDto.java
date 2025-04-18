package com.techie.microservices.menu.dto.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.techie.microservices.menu.Enums.InputMethod;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    @NotNull
    @Size(max = 200, message = "Maximum length for Process Name is 200 characters.")
    private String name;

    @NotNull
    private boolean status;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private InputMethod inputMethod;

    @NotNull
    private String createdBy;

    private List<Integer> checkListIds;

}

