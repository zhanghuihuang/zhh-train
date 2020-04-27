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
public enum AuthorityTypeEnum implements IEnum {
    UR("用户权限"),
    CR("客户端权限"),
    ;

    private String desc;
}
