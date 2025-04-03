package com.techie.microservices.menu.dto.common;

import lombok.Data;

@Data
public class PagingRequestParameters {
    private static final int MAX_PAGE_SIZE = 50;

    private int pageIndex = 1;

    private int pageSize = 10;

    public void setPageSize(int pageSize) {
        this.pageSize = Math.min(pageSize, MAX_PAGE_SIZE);
    }
}