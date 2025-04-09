package com.techie.microservices.menu.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateFormLoadDto {
    private String updatedBy;
    private String path;
    private String status;
    private String note;
}

