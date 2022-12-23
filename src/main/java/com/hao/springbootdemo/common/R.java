package com.hao.springbootdemo.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 定义Json响应数据
 *
 * @author xu.liang
 * @since 2022/12/15 09:37
 */
@ApiModel
@Data
@NoArgsConstructor
public class R<T> implements Serializable {

    @ApiModelProperty(notes = "响应码 200: 成功，其他: 异常")
    private Integer code;

    @ApiModelProperty(notes = "异常描述信息")
    private String msg;

    @ApiModelProperty(notes = "返回的数据")
    private T data;

    public R(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功
     */
    public static <E> R<E> success(E data) {
        return new R<E>(200, null, data);
    }

    /**
     * 成功
     */
    public static <E> R<E> success() {
        return new R<E>(200, null, null);
    }


    /**
     * 失败
     */
    public static R error(String msg) {
        R result = new R();
        result.setCode(500);
        result.setMsg(msg);
        return result;
    }

    /**
     * 失败
     */
    public static R error(Integer code, String msg) {
        R result = new R();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /**
     * 失败
     */
    public static R error() {
        R result = new R();
        result.setCode(500);
        result.setMsg("未知异常，请联系管理员");
        return result;
    }

    /**
     * 添加返回的数据
     */
    public R<T> put(T data) {
        this.data = data;
        return this;
    }

    /**
     * 是否正常
     */
    @JsonIgnore
    public boolean isSuccess() {
        return this.code == 200;
    }

    @JsonIgnore
    public boolean isError() {
        return this.code != 200;
    }
}
