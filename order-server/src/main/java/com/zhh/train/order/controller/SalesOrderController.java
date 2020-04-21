package com.zhh.train.order.controller;

import com.zhh.train.order.dto.SalesOrderDto;
import com.zhh.train.order.entity.SalesOrder;
import com.zhh.train.order.service.inventory.InventoryV1ApiClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : zhanghuihuang
 * @project : zhh-train
 * @description : //TODO
 * @date : 2020/4/11 3:48 下午
 */
@RestController
@RequestMapping("/salesOrder")
public class SalesOrderController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private InventoryV1ApiClient inventoryV1ApiClient;

    private static final ConcurrentSkipListMap<Integer, SalesOrder> salesOrderMap = new ConcurrentSkipListMap<>();

    private static final AtomicInteger count = new AtomicInteger(0);

    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody SalesOrderDto salesOrderDto) {
        SalesOrder salesOrder = modelMapper.map(salesOrderDto, SalesOrder.class);
        int i = count.addAndGet(1);
        salesOrder.setOrderNo(i);
        salesOrderMap.put(salesOrder.getOrderNo(), salesOrder);
        //请求库存,扣库存数量
        String s = inventoryV1ApiClient.decrementInventory(salesOrder.getProduct(), salesOrder.getQuantity());
        return ResponseEntity.ok(salesOrder.getOrderNo() + ",扣库存结果:" + s);
    }

    @PostMapping("/timeout")
    public ResponseEntity<String> placeOrderTimeout(@RequestBody SalesOrderDto salesOrderDto) {
        SalesOrder salesOrder = modelMapper.map(salesOrderDto, SalesOrder.class);
        int i = count.addAndGet(1);
        salesOrder.setOrderNo(i);
        salesOrderMap.put(salesOrder.getOrderNo(), salesOrder);
        //请求库存,扣库存数量
        String s = inventoryV1ApiClient.decrementInventoryTimeout(salesOrder.getProduct(), salesOrder.getQuantity());
        System.out.println("扣减库存结果:" + s);
        return ResponseEntity.ok(salesOrder.getOrderNo() + ",扣库存结果:" + s);
    }

    @PostMapping("/fallback")
    public ResponseEntity<String> placeOrderFallback(@RequestBody SalesOrderDto salesOrderDto) {
        SalesOrder salesOrder = modelMapper.map(salesOrderDto, SalesOrder.class);
        int i = count.addAndGet(1);
        salesOrder.setOrderNo(i);
        salesOrderMap.put(salesOrder.getOrderNo(), salesOrder);
        //请求库存,扣库存数量
        String s = inventoryV1ApiClient.decrementInventoryFallback(salesOrder.getProduct(), salesOrder.getQuantity());
        System.out.println("扣减库存结果:" + s);
        return ResponseEntity.ok(salesOrder.getOrderNo() + ",扣库存结果:" + s);
    }

    @PostMapping("/coreSize")
    public ResponseEntity<String> placeOrderCoreSize(@RequestBody SalesOrderDto salesOrderDto) {
        SalesOrder salesOrder = modelMapper.map(salesOrderDto, SalesOrder.class);
        int i = count.addAndGet(1);
        salesOrder.setOrderNo(i);
        salesOrderMap.put(salesOrder.getOrderNo(), salesOrder);
        //请求库存,扣库存数量
        String s = inventoryV1ApiClient.decrementInventoryCoreSize(salesOrder.getProduct(), salesOrder.getQuantity());
        System.out.println("扣减库存结果:" + s);
        return ResponseEntity.ok(salesOrder.getOrderNo() + ",扣库存结果:" + s);
    }

    @PostMapping("/circuitBreaker")
    public ResponseEntity<String> placeOrderCircuitBreaker(@RequestBody SalesOrderDto salesOrderDto) {
        SalesOrder salesOrder = modelMapper.map(salesOrderDto, SalesOrder.class);
        int i = count.addAndGet(1);
        salesOrder.setOrderNo(i);
        salesOrderMap.put(salesOrder.getOrderNo(), salesOrder);
        //请求库存,扣库存数量
        String s = inventoryV1ApiClient.decrementInventoryCircuitBreaker(salesOrder.getProduct(), salesOrder.getQuantity());
        System.out.println("扣减库存结果:" + s);
        return ResponseEntity.ok(salesOrder.getOrderNo() + ",扣库存结果:" + s);
    }

    @PostMapping("/circuitBreakerFallback")
    public ResponseEntity<String> placeOrderCircuitBreakerFallback(@RequestBody SalesOrderDto salesOrderDto) {
        SalesOrder salesOrder = modelMapper.map(salesOrderDto, SalesOrder.class);
        int i = count.addAndGet(1);
        salesOrder.setOrderNo(i);
        salesOrderMap.put(salesOrder.getOrderNo(), salesOrder);
        //请求库存,扣库存数量
        String s = inventoryV1ApiClient.decrementInventoryCircuitBreakerFallback(salesOrder.getProduct(), salesOrder.getQuantity());
        System.out.println("扣减库存结果:" + s);
        return ResponseEntity.ok(salesOrder.getOrderNo() + ",扣库存结果:" + s);
    }

    @GetMapping("/{orderNo}")
    public ResponseEntity<SalesOrder> loadSalesOrder(@PathVariable String orderNo) {
        SalesOrder salesOrder = salesOrderMap.get(orderNo);
        return ResponseEntity.ok(salesOrder);
    }

    @GetMapping
    public ResponseEntity<Collection<SalesOrder>> salesOrderList() {
        return ResponseEntity.ok(salesOrderMap.values());
    }

    @GetMapping("/inventory")
    public ResponseEntity<Map> inventoryList() {
        return ResponseEntity.ok(inventoryV1ApiClient.inventoryDetail());
    }
}
