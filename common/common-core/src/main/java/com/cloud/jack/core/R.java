package com.cloud.jack.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 响应信息主体
 *
 * @author ML
 */
@Builder
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@Data
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code;

    private String msg;

    private T data;

    public R() {
        super();
        this.code = ApiCode.OK;
        this.msg = ApiCode.OK_MSG;
    }

    public R(T data) {
        super();
        this.data = data;
        this.code = ApiCode.OK;
        this.msg = ApiCode.OK_MSG;
    }

    public R(T data, String msg) {
        super();
        this.data = data;
        this.msg = msg;
        this.code = ApiCode.OK;
    }

    public R(Throwable e) {
        super();
        this.msg = e.getMessage();
        this.code = ApiCode.ERROR;
    }

    public static R ok() {
        return R.builder().code(ApiCode.OK).msg(ApiCode.OK_MSG).build();
    }

    public static R ok(String msg) {
        return R.builder().code(ApiCode.OK).msg(msg).build();
    }

    public static <T> R ok(T data) {
        return  R.builder().code(ApiCode.OK).msg(ApiCode.OK_MSG).data(data).build();
    }

    public static <T> R ok(String msg, T data) {
        return R.builder().code(ApiCode.OK).msg(msg).data(data).build();
    }

    public static R fail() {
        return R.builder().code(ApiCode.ERROR).msg(ApiCode.ERROR_MSG).build();
    }

    public static R fail(String msg) {
        return R.builder().code(ApiCode.ERROR).msg(msg).build();
    }

    public static R fail(Integer code, String msg) {
        return R.builder().code(code).msg(msg).build();
    }

    public static <T> R fail(Integer code, String msg, T data) {
        return R.builder().code(code).msg(msg).data(data).build();
    }
}
