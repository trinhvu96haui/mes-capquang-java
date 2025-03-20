package com.techie.microservices.menu.extensions;

import java.util.function.Predicate;
import java.util.stream.Stream;
import com.techie.microservices.menu.dto.common.PagingDto;
import com.techie.microservices.menu.dto.common.PagingRequestParameters;

public class QueryableExtensions<T> {
    private Stream<T> query;

    public QueryableExtensions(Stream<T> query) {
        this.query = query;
    }

    public QueryableExtensions<T> whereIf(boolean condition, Predicate<T> predicate) {
        if (condition) {
            this.query = this.query.filter(predicate);
        }
        return this;  // Trả về chính nó để gọi tiếp phương thức khác
    }

    public PagingDto<T> paginate(PagingRequestParameters paginationQuery) {
        int pageIndex = Math.max(paginationQuery.getPageIndex(), 1);
        int pageSize = Math.max(paginationQuery.getPageSize(), 15);

        List<T> fullList = this.query.toList();  // Dùng `this`
        int total = fullList.size();

        List<T> paginatedData = fullList.stream()
                .skip((long) (pageIndex - 1) * pageSize)
                .limit(pageSize)
                .toList();

        return new PagingDto<>(paginatedData, total, pageIndex, pageSize);
    }
}

