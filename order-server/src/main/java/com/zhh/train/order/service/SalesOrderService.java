package com.zhh.train.order.service;

import com.zhh.train.order.dto.SalesOrderDto;
import com.zhh.train.order.entity.SalesOrder;

import java.util.List;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/4/20 12:34 下午
 */
public interface SalesOrderService {
    SalesOrder buyGoods(SalesOrderDto salesOrderDto);

    SalesOrder buyGoodsTimeout(SalesOrderDto salesOrderDto);

    SalesOrder buyGoodsError(SalesOrderDto salesOrderDto);

    SalesOrder buyGoodsCoreSize(SalesOrderDto salesOrderDto);

    SalesOrder buyGoodsCircuitBreaker(SalesOrderDto salesOrderDto);

    SalesOrder buyGoodsCircuitBreakerFallback(SalesOrderDto salesOrderDto);

    SalesOrder loadSalesOrder(Integer orderNo);

    List<SalesOrder> findAll();
}
