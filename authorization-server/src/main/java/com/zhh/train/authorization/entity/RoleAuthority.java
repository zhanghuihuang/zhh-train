package com.zhh.train.authorization.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/4/25 1:21 下午
 */
/*@Data
@Entity
@Table(name = "t_role_authority")
@EntityListeners(AuditingEntityListener.class)*/
public class RoleAuthority extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Role role;
    private Authority authority;
}
