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
@Table(name = "t_user_role")
@EntityListeners(AuditingEntityListener.class)*/
public class UserRole extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private User user;
    private Role role;
}
