package com.handsUp.ego_v2.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Xuuxxi
 * @Date: 2022/5/3
 */
@Data
public class R<T> {
    private Integer code;

    private String msg;

    private T data;

    private Map map = new HashMap();

    public static <T> R<T> success(T object){
        R<T> r = new R<T>();
        r.data = object;
        r.code = 1;
        return r;
    }

    public static <T> R<T> error(String msg){
        R<T> r = new R<>();
        r.code = 0;
        r.msg = msg;

        return r;
    }

    public R<T> add(String key,String value){
        this.map.put(key,value);
        return this;
    }
}
