package com.zhh.train.authorization.enums;

/**
 * @author : page
 * @project : zhh-train
 * @description : 枚举类统一接口
 * @date : 2020/4/25 7:05 下午
 */
public interface IEnum {
    default String getCode() {
        return String.valueOf(ordinal());
    }

    default String getName() {
        return name();
    }

    String getDesc();

    int ordinal();

    String name();
}
