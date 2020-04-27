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
public enum RoleTypeEnum implements IEnum {
    UR("用户角色"),
    CR("客户端角色"),
    ;

    private String desc;
}
