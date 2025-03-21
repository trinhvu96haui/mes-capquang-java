package com.techie.microservices.menu.dto.requests;

import jakarta.validation.constraints.Max;
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
public class CreateProductDto {
    @NotNull
    private Integer productId;

    @NotNull
    @Size(max = 100)
    private String value;

    private String createdBy;

    @NotNull
    @Size(max = 200)
    private String name;

    @NotNull
    private Integer modelId;

    @NotNull
    private Integer uomId;

    private String active;
    private String type;

//    public Integer getProductId() {
//        return this.productId;
//    }

}

