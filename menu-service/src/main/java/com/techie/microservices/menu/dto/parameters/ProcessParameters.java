package com.techie.microservices.menu.dto.parameters;
import com.techie.microservices.menu.dto.common.PagingRequestParameters;
import lombok.Data;

@Data
public class ProcessParameters extends PagingRequestParameters {
    private String keyword;
}
