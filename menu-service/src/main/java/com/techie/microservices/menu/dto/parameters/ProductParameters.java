package com.techie.microservices.menu.dto.parameters;

@Data
public class ProductParameters extends PagingRequestParameters {
    private Integer modelId;
    private String keyword;
}
