package com.zhh.train.inventory.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author : zhanghuihuang
 * @project : zhh-train
 * @description : //TODO
 * @date : 2020/4/11 3:44 下午
 */
@Entity
@Table(name = "t_inventory")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Inventory implements Serializable {
    @Id
    @GenericGenerator(name = "manual_assigned", strategy = "assigned")
    @GeneratedValue(generator = "manual_assigned")
    private String product;
    private int quantity = 0;
    @CreatedDate
    private Date createAt;
    @CreatedBy
    private String createBy;
    @LastModifiedDate
    private Date updateAt;
    @LastModifiedBy
    private String updateBy;
}
