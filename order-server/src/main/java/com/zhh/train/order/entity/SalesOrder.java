package com.zhh.train.order.entity;

import lombok.Data;
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
 * @date : 2020/4/11 3:42 下午
 */
@Entity
@Table(name = "t_sales_order")
@Data
@EntityListeners(AuditingEntityListener.class)
public class SalesOrder implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderNo;
    private String customer;
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
    @Transient
    private String message;
}
