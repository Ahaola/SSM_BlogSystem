package com.example.demo.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 统一数据格式返回
 */
@Data
public class AjaxResult implements Serializable {
    // 状态码
    private Integer code;
    // 状态码描述信息
    private String msg;
    // 返回的数据
    private Object data;

    /**
     * 操作成功返回的结果
     */
    public static AjaxResult success(Object data) {
        AjaxResult result = new AjaxResult();
        result.setCode(200);
        result.setMsg("");
        result.setData(data);
        return result;
    }

    public static AjaxResult success(int code, Object data) {
        AjaxResult result = new AjaxResult();
        result.setCode(code);
        result.setMsg("");
        result.setData(data);
        return result;
    }

    public static AjaxResult success(int code, String msg, Object data) {
        AjaxResult result = new AjaxResult();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    /**
     * 返回失败结果
     */
    public static AjaxResult fail(int code, String msg) {
        AjaxResult result = new AjaxResult();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    public static AjaxResult fail(int code, String msg, Object data) {
        AjaxResult result = new AjaxResult();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

}
