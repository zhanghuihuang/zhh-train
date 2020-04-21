package com.zhh.train.order.dto;

import lombok.Data;

/**
 * @author : zhanghuihuang
 * @project : zhh-train
 * @description : //TODO
 * @date : 2020/4/11 3:55 下午
 */
@Data
public class SalesOrderDto {
    private Integer id;
    private String customer;
    private String product;
    private int quantity;
}
