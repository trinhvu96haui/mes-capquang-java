package com.techie.microservices.menu.extensions;

import com.techie.microservices.menu.dto.common.PagingDto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class AutoMapperExtension {

    private static final ModelMapper mapper = new ModelMapper();

    // Map một danh sách từ Source -> Target
    public static <TSource, TTarget> List<TTarget> mapList(List<TSource> sourceList, Class<TTarget> targetClass) {
        if (sourceList == null) return List.of();
        return sourceList.stream()
                .map(item -> mapper.map(item, targetClass))
                .collect(Collectors.toList());
    }

    // Map PagingDto từ Source -> Target
    public static <TSource, TTarget> PagingDto<TTarget> mapPagingList(PagingDto<TSource> sourcePaginationList, Class<TTarget> targetClass) {
        if (sourcePaginationList == null) return new PagingDto<>();

        List<TTarget> result = sourcePaginationList.getData().stream()
                .map(item -> mapper.map(item, targetClass))
                .collect(Collectors.toList());

        return new PagingDto<>(result, sourcePaginationList.getTotal(), sourcePaginationList.getPageIndex(), sourcePaginationList.getPageSize());
    }
}
