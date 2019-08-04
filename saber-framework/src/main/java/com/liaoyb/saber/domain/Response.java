package com.liaoyb.saber.domain;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * 响应对象
 *
 * @author liaoyb
 */
@Data
public class Response<T> {
    /**
     * 响应码（前三位与http请求码一致，后面的为业务码）
     */
    private int code;

    /**
     * 数据
     */
    private T data;

    /**
     * 人性化的错误信息
     */
    private String message;

    /**
     * 详细错误信息(一级一级传递，最后为当前错误信息)
     */
    private List<Error> errors;

    /**
     * 成功
     *
     * @param data 数据
     * @param <T>
     * @return 响应
     */
    public static <T> Response<T> success(T data) {
        Response<T> response = new Response<>();
        response.setCode(HttpStatus.OK.value());
        response.setData(data);
        return response;
    }

    /**
     * 成功
     *
     * @return 响应
     */
    public static Response<Void> success() {
        return success(null);
    }

    /**
     * 错误
     *
     * @param code    响应码
     * @param message 消息
     * @param errors  错误
     * @param <T>
     * @return 响应
     */
    public static <T> Response<T> error(int code, String message, List<Error> errors) {
        Response<T> response = new Response<>();
        response.setCode(code);
        response.setMessage(message);
        response.setErrors(errors);
        return response;
    }

    /**
     * 错误
     *
     * @param httpStatus http响应
     * @param message    错误信息
     * @param <T>
     * @return 响应
     */
    public static <T> Response<T> error(HttpStatus httpStatus, String message) {
        return error(httpStatus.value(), message, null);
    }

    /**
     * 错误（服务器错误500）
     *
     * @param message 错误信息
     * @param <T>
     * @return 响应
     */
    public static <T> Response<T> error(String message) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    /**
     * 错误（服务器错误500）
     *
     * @param message 错误信息
     * @param errors  错误
     * @param <T>
     * @return 响应
     */
    public static <T> Response<T> error(String message, List<Error> errors) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, errors);
    }


}
