package com.liaoyb.saber.domain;

import lombok.Data;

/**
 * 错误
 *
 * @author liaoyb
 */
@Data
public class Error {
    /**
     * 错误码
     */
    private int code;
    /**
     * 错误消息
     */
    private String message;
}
