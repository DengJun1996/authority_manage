package com.dj.common;

import lombok.Data;

import java.util.List;

/**
 * 返回请求数据格式
 *
 * @author wxl
 * @date 2020-03-06 下午2:46
 * @company www.dfdk.com.cn
 */
@Data
public class ResEntity<T> {

    private static final int SUCCESS = 0;

    private static final int ERROR = -1;

    public static final int UNKONW_ERROR = -255;

    private static final String DEFAULT_MSG = "请求成功";

    private static final int DEFAULT_COUNT = 0;

    private Boolean success = true;

    private int code;
    private int count = DEFAULT_COUNT;
    private String msg = DEFAULT_MSG;
    private T data;

    public static <T> ResEntity<T> SUCCESS() {
        return new ResEntity<>(SUCCESS);
    }

    public static <T> ResEntity<T> OK(T data) {
        return new ResEntity<>(data);
    }

    public static <T> ResEntity<T> OK(T data, int count) {
        return new ResEntity<>(data, count);
    }

    public static <T> ResEntity<T> OK(int code, T data) {
        return new ResEntity<>(code, data);
    }

    public static <T> ResEntity<T> SUCCESS(String msg) {
        return new ResEntity<>(SUCCESS, msg);
    }

    public static <T> ResEntity<T> SUCCESS(T data) {
        return new ResEntity<>(data);
    }

    public static <T> ResEntity<T> SUCCESS(String msg, Boolean success){
        return new ResEntity<>(SUCCESS,msg,success);
    }

    public static <T> ResEntity<T> SUCCESS(int code, T data) {
        return new ResEntity<>(data);
    }

    public static <T> ResEntity<T> ERROR(int code, String msg) {
        return new ResEntity<>(code, msg);
    }

    public static <T> ResEntity<T> ERROR(ResultEnum resultEnum) {
        return new ResEntity<>(resultEnum.getCode(), resultEnum.getMsg());
    }

    public static <T> ResEntity<T> ERROR(String msg) {
        return new ResEntity<>(-1, msg);
    }
    public static <T> ResEntity<T> ERROR(String msg, Boolean error){
        return new ResEntity<>(ERROR,msg,error);
    }

    public ResEntity(String msg) {
        this.code = 1;
        this.msg = msg;
    }

    public ResEntity(int code) {
        this.code = code;
    }

    public ResEntity(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResEntity() {
        this.code = 1;
    }

    public ResEntity(T data) {
        this.code = 0;
        if (data instanceof List) {
            List<T> list = (List<T>) data;
            this.data = data;
            this.count = list.size();
        } else {
            this.data = data;
            this.count = 1;
        }
    }

    public ResEntity(int code, T data){
        this.code = code;
        this.data = data;
    }

    /**
     * 分页专用
     * @param data
     * @param totalCount
     */
    public ResEntity(T data, int totalCount) {
        this.code = 0;
        this.data = data;
        this.count = totalCount;
    }

    public ResEntity(int code, T data, String msg){
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public ResEntity(int code, String msg, Boolean success){
        this.code = code;
        this.msg = msg;
        this.success = success;
    }

    public boolean isSuccess() {
        return this.code == SUCCESS ? Boolean.TRUE : Boolean.FALSE;
    }
}
