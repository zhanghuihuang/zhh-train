package com.zhh.train.authorization.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/4/25 7:40 下午
 */
@AllArgsConstructor
public class AuthClientVo implements ClientDetails, CredentialsContainer {

    private String clientName;
    @JsonIgnore
    private String password;
    private Set<String> resourceIds;
    private Set<String> scope;
    private Set<String> grantTypes;
    private Set<String> registeredRedirectUri;
    private Set<AuthAuthorityVo> authAuthorityVos;
    private Integer accessTokenValiditySeconds;
    private Integer refreshTokenValiditySeconds;
    private Map<String, Object> additionalInformation;

    /**
     * 客户端id
     *
     * @return
     */
    @Override
    public String getClientId() {
        return this.clientName;
    }

    /**
     * 该客户端可以访问的资源。 如果为空，则可以被调用方忽略。
     *
     * @return
     */
    @Override
    public Set<String> getResourceIds() {
        return this.resourceIds;
    }

    /**
     * 验证此客户端是否需要密码。
     *
     * @return
     */
    @Override
    public boolean isSecretRequired() {
        return this.password != null && this.password.trim().length() > 0;
    }

    @JsonIgnore
    @Override
    public String getClientSecret() {
        return this.password;
    }

    /**
     * 是否将此客户端限制在特定范围内。如果为false，则将忽略身份验证请求的范围。
     *
     * @return
     */
    @Override
    public boolean isScoped() {
        return this.getScope() != null && this.getScope().size() > 0;
    }

    /**
     * 这个客户的范围。如果客户端没有作用域，则为空。
     *
     * @return
     */
    @Override
    public Set<String> getScope() {
        return this.scope;
    }

    /**
     * 该客户端被授权的授权类型。
     *
     * @return
     */
    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return this.grantTypes;
    }

    /**
     * 该客户端在“ authorization_code”访问授权期间使用的预定义重定向URI。 请参阅OAuth规范，第4.1.1节。
     *
     * @return
     */
    @Override
    public Set<String> getRegisteredRedirectUri() {
        return this.registeredRedirectUri;
    }

    /**
     * 返回授予OAuth客户端的权限。 无法返回<空值>。
     * 注意，这些不是使用授权访问令牌授予用户的权限，而是客户端本身固有的权限。
     *
     * @return
     */
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return (HashSet) authAuthorityVos;
    }

    /**
     * 此客户端的访问令牌有效期。如果未明确设置，则为Null（实现可能使用该事实来提供例如默认值）。
     *
     * @return
     */
    @Override
    public Integer getAccessTokenValiditySeconds() {
        return this.accessTokenValiditySeconds;
    }

    /**
     * 此客户端的刷新令牌有效期。 空值表示令牌服务设置的默认值，零值或负值表示未到期的令牌。
     *
     * @return
     */
    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return this.refreshTokenValiditySeconds;
    }

    /**
     * 判断客户端是否需要特定范围的用户批准。
     *
     * @param scope
     * @return
     */
    @Override
    public boolean isAutoApprove(String scope) {
        if (this.isScoped()) {
            return this.getScope().contains(scope);
        }
        return false;
    }

    /**
     * 这个客户机的附加信息，不是vanilla OAuth协议所需要的，但可能对存储描述性信息很有用。
     *
     * @return
     */
    @Override
    public Map<String, Object> getAdditionalInformation() {
        return this.additionalInformation;
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }
}
