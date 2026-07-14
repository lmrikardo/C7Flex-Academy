package br.com.c7flex.academia.common.response;

import java.util.List;

public record PageResponse<T>(

        List<T> content,

        long totalElements,

        int totalPages,

        int page,

        int size,

        boolean first,

        boolean last

) {
}