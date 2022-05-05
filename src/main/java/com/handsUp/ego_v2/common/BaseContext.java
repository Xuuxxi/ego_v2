package com.handsUp.ego_v2.common;

/**
 * 工具类：在线程中获取当前登录用户Id
 * 配合Filter
 * @Author: Xuuxxi
 * @Date: 2022/5/5
 */

public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
