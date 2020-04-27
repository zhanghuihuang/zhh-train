package com.zhh.train.authorization.entity;

import com.zhh.train.authorization.enums.RoleTypeEnum;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/4/25 9:22 下午
 */
@Data
@Entity
@Table(name = "t_role")
@EntityListeners(AuditingEntityListener.class)
public class Role extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", unique = true)
    private String name;
    private String desc;
    @Enumerated(EnumType.STRING)
    private RoleTypeEnum type;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "t_role_authority", joinColumns = @JoinColumn(name = "role_name"),
            inverseJoinColumns = @JoinColumn(name = "authority_name", referencedColumnName = "name"))
    private List<Authority> authorities;
}
