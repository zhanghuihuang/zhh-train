package com.zhh.train.authorization.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/4/25 9:09 下午
 */
@Getter
@AllArgsConstructor
public class AuthAuthorityVo implements GrantedAuthority, Serializable {
    private Long id;
    // 名称
    private String name;
    // 描述
    private String description;

    @Override
    public String getAuthority() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AuthAuthorityVo that = (AuthAuthorityVo) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
