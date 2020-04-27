package com.zhh.train.authorization.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zhh.train.authorization.enums.StatusEnum;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/4/25 1:21 下午
 */
@Data
@Entity
@Table(name = "t_client")
@EntityListeners(AuditingEntityListener.class)
public class Client extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "client_name", unique = true)
    private String clientName;
    @JsonIgnore
    private String password;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    @Column(name = "grant_types")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> grantTypes;
    @Column(name = "resource_ids")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> resourceIds;
    @Column(name = "registered_redirect_uri")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> registeredRedirectUri;
    @Column(name = "scope")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> scope;
    private Integer accessTokenValiditySeconds;
    private Integer refreshTokenValiditySeconds;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "t_client_role", joinColumns = @JoinColumn(name = "client_name"),
            inverseJoinColumns = @JoinColumn(name = "role_name", referencedColumnName = "name"))
    private List<Role> roles;
}
