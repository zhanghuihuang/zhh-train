package com.zhh.train.authorization.entity;

import com.zhh.train.authorization.enums.AuthorityTypeEnum;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/4/25 9:21 下午
 */
@Data
@Entity
@Table(name = "t_authority")
@EntityListeners(AuditingEntityListener.class)
public class Authority extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", unique = true)
    private String name;
    private String desc;
    @Enumerated(EnumType.STRING)
    private AuthorityTypeEnum type;
}
