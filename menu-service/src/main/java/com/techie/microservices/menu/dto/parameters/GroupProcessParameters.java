package com.techie.microservices.menu.dto.parameters;
import com.techie.microservices.menu.dto.common.PagingRequestParameters;
import lombok.Data;

@Data
public class GroupProcessParameters extends PagingRequestParameters {
    private String keyword;
}
