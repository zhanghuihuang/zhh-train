package com.zhh.train.order.controller;

import com.zhh.train.order.dto.SalesOrderDto;
import com.zhh.train.order.entity.SalesOrder;
import com.zhh.train.order.service.SalesOrderService;
import com.zhh.train.order.service.inventory.InventoryV3ApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author : zhanghuihuang
 * @project : zhh-train
 * @description : //TODO
 * @date : 2020/4/11 3:48 下午
 */
@RestController
@RequestMapping("/salesOrder/v3")
public class SalesOrderV3Controller {
    @Autowired
    @Qualifier("salesOrderV3Service")
    private SalesOrderService salesOrderService;

    @Autowired
    private InventoryV3ApiClient inventoryService;

    @PostMapping
    public ResponseEntity<SalesOrder> buyGoods(@RequestBody SalesOrderDto salesOrderDto) {
        SalesOrder salesOrder = salesOrderService.buyGoods(salesOrderDto);
        return ResponseEntity.ok(salesOrder);
    }

    /**
     * execution.isolation.thread.timeoutInMilliseconds 配置调用超时2000毫秒,则执行fallback
     *
     * @param salesOrderDto
     */
    @PostMapping("/timeout")
    public ResponseEntity<SalesOrder> buyGoodsTimeout(@RequestBody SalesOrderDto salesOrderDto) {
        SalesOrder salesOrder = salesOrderService.buyGoodsTimeout(salesOrderDto);
        return ResponseEntity.ok(salesOrder);
    }

    /**
     * 模拟库存服务不可用时,启用备用方案
     *
     * @param salesOrderDto
     * @return
     */
    @PostMapping("/fallback")
    public ResponseEntity<SalesOrder> buyGoodsFallback(@RequestBody SalesOrderDto salesOrderDto) {
        SalesOrder salesOrder = salesOrderService.buyGoodsError(salesOrderDto);
        return ResponseEntity.ok(salesOrder);
    }

    @PostMapping("/coreSize")
    public ResponseEntity<SalesOrder> buyGoodsCoreSize(@RequestBody SalesOrderDto salesOrderDto) {
        SalesOrder salesOrder = salesOrderService.buyGoodsCoreSize(salesOrderDto);
        return ResponseEntity.ok(salesOrder);
    }

    /**
     * 3秒内,请求次数达到两次,且失败率在50%以上的打开断路器
     * 跳闸后,3秒内要是有一个请求通过的,关闭断路器
     * metrics.rollingStats.timeInMilliseconds 设置统计窗口,默认10s
     * circuitBreaker.requestVolumeThreshold 窗口时间内最小请求次数,默认20次
     * circuitBreaker.errorThresholdPercentage 窗口时间内请求失败率,默认
     * circuitBreaker.sleepWindowInMilliseconds 跳闸后尝试请求时间窗口,默认5s
     *
     * @param salesOrderDto
     * @return
     */
    @PostMapping("/circuitBreaker")
    public ResponseEntity<SalesOrder> buyGoodsCircuitBreaker(@RequestBody SalesOrderDto salesOrderDto) {
        SalesOrder salesOrder = salesOrderService.buyGoodsCircuitBreaker(salesOrderDto);
        return ResponseEntity.ok(salesOrder);
    }

    /**
     * 3秒内,请求次数达到两次,且失败率在50%以上的打开断路器
     * 跳闸后,3秒内要是有一个请求通过的,关闭断路器
     * metrics.rollingStats.timeInMilliseconds 设置统计窗口,默认10s
     * circuitBreaker.requestVolumeThreshold 窗口时间内最小请求次数,默认20次
     * circuitBreaker.errorThresholdPercentage 窗口时间内请求失败率,默认
     * circuitBreaker.sleepWindowInMilliseconds 跳闸后尝试请求时间窗口,默认5s
     *
     * @param salesOrderDto
     * @return
     */
    @PostMapping("/circuitBreakerFallback")
    public ResponseEntity<SalesOrder> buyGoodsCircuitBreakerFallback(@RequestBody SalesOrderDto salesOrderDto) {
        SalesOrder salesOrder = salesOrderService.buyGoodsCircuitBreakerFallback(salesOrderDto);
        return ResponseEntity.ok(salesOrder);
    }

    @GetMapping("/{orderNo}")
    public ResponseEntity<SalesOrder> loadSalesOrder(@PathVariable Integer orderNo) {
        SalesOrder salesOrder = salesOrderService.loadSalesOrder(orderNo);
        return ResponseEntity.ok(salesOrder);
    }

    @GetMapping
    public ResponseEntity<List<SalesOrder>> salesOrderList() {
        List<SalesOrder> list = salesOrderService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/inventory")
    public ResponseEntity<Map> inventoryList() {
        return ResponseEntity.ok(inventoryService.inventoryDetail());
    }
}
