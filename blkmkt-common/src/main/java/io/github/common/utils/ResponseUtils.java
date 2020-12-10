package io.github.common.utils;

import io.github.common.entity.Response;
import io.github.common.entity.ResponseWithData;
import org.apache.http.HttpStatus;

public class ResponseUtils {
    public static Response ok() {
        return new Response(200, "success");
    }

    public static <T> ResponseWithData<T> ok(T data) {
        return new ResponseWithData<>(200, "success", data);
    }

    public static Response error() {
        return new Response(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
    }

    public static Response error(String msg) {
        return new Response(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static Response error(int code, String msg) {
        return new Response(code, msg);
    }

    public static <T> ResponseWithData<T> error(int code, String message, T data) {
        return new ResponseWithData<>(code, message, data);
    }
}
