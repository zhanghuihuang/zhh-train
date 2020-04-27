package com.zhh.train.authorization.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/4/25 7:04 下午
 */
@Getter
@AllArgsConstructor
public enum StatusEnum implements IEnum {
    ENABLED("已启用"),
    EXPIRED("已过期"),
    LOCKED("已锁定"),
    DELETED("已删除"),
    ;

    private String desc;
}
