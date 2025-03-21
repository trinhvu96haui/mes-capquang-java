package com.techie.microservices.menu.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagingDto<T> {
    private List<T> data = new ArrayList<>();
    private int total = 0;
    private int pageIndex = 1;
    private int pageSize = 30;
}

