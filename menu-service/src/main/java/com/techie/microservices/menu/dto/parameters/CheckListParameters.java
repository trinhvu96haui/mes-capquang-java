package com.techie.microservices.menu.dto.parameters;
import com.techie.microservices.menu.Enums.CheckListType;
import com.techie.microservices.menu.dto.common.PagingRequestParameters;
import lombok.Data;

@Data
public class CheckListParameters extends PagingRequestParameters {
    private String keyword;
    private CheckListType type;
}
