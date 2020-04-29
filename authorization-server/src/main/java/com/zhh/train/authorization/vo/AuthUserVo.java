package com.zhh.train.authorization.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zhh.train.authorization.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/4/25 7:40 下午
 */
@Getter
@AllArgsConstructor
public class AuthUserVo implements UserDetails, CredentialsContainer {
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private StatusEnum status;

    private Set<AuthAuthorityVo> authorities;

    @Override
    public Set<AuthAuthorityVo> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return status != StatusEnum.EXPIRED;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status != StatusEnum.LOCKED;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status == StatusEnum.ENABLED;
    }

    /**
     * 删除凭据,用于清除敏感信息
     */
    @Override
    public void eraseCredentials() {
        this.password = null;
    }
}
