package com.devtw.store.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {

    public enum Status {
        SUCCESS, FAIL, ERROR
    }

    private final Status status;
    private final String errorMessage;
    private final T data;

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(Status.SUCCESS, null, data);
    }

//    public static <T> ApiResponse<List<T>> successList(List<T> dataList) {
//        return new ApiResponse<>(Status.SUCCESS, null, dataList);
//    }

    public static <T> ApiResponse<T> fail(String message) {
        return new ApiResponse<>(Status.FAIL, message, null);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(Status.ERROR, message, null);
    }
}
