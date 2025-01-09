package com.example.common.util.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DataResponse<T> {
    private boolean success;
    private T data;

    public static <T> DataResponse<T> response(final boolean success, final T data) {
        return DataResponse.<T>builder()
                .success(success)
                .data(data)
                .build();
    }
}
