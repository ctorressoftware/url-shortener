package io.github.ctorressoftware.urlshortener.infrastructure.web.shared;

public class ApiResponse<T> {
    private final boolean success;
    private final String code;
    private final String message;
    private final Object errors;
    private final T data;

    private ApiResponse(boolean success, String code, String message, Object errors, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.errors = errors;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(
                true,
                "OK",
                "Success",
                null,
                data);
    }

    public static <T> ApiResponse<T> error(String code, String message, Object errors) {
        return new ApiResponse<>(
                false,
                code,
                message,
                errors,
                null);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getErrors() {
        return errors;
    }

    public T getData() {
        return data;
    }
}
