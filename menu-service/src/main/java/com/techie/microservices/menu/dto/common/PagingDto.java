package com.techie.microservices.menu.dto.common;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagingDto<T> {
    private List<T> data = new ArrayList<>();
    private int total = 0;
    private int pageIndex = 1;
    private int pageSize = 30;
}

