package com.zhh.train.authorization.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/4/25 9:22 下午
 */
@Data
@MappedSuperclass
public class BaseEntity<ID extends Serializable> {
    @Id
    private ID id;
    @CreatedDate
    private Date createAt;
    @CreatedBy
    private String createBy;
    @LastModifiedDate
    private Date updateAt;
    @LastModifiedBy
    private String updateBy;
}
