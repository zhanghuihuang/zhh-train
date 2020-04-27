package com.zhh.train.authorization.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/4/26 11:15 上午
 */
@Data
public class UserSaveDto implements Serializable {
    private String username;
    private String password;
}
