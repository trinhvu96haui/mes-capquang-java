package com.techie.microservices.menu.dto.parameters;
import com.techie.microservices.menu.dto.common.PagingRequestParameters;
import lombok.Data;

@Data
public class ProductParameters extends PagingRequestParameters {
    private Integer modelId;
    private String keyword;
}
