package com.zhh.train.inventory.controller;

import com.zhh.train.inventory.api.service.InventoryV3Api;
import com.zhh.train.inventory.service.InventoryService;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : zhanghuihuang
 * @project : zhh-train
 * @description : //TODO
 * @date : 2020/4/11 5:33 下午
 */
@RestController
public class InventoryController implements InventoryV3Api {
    @Autowired
    private InventoryService inventoryService;
    private static volatile AtomicInteger visitCount = new AtomicInteger(0);

    @Override
    public String decrementInventory(@PathVariable String product, @PathVariable int quantity) {
        return inventoryService.decrementInventory(product, quantity);
    }

    @Override
    public String decrementInventoryTimeout(@PathVariable String product, @PathVariable int quantity) {
        int i = visitCount.addAndGet(1);
        try {
            int timeout = RandomUtils.nextInt(3000) + 2000;
            System.out.println("访问次数:" + i + ",耗时:" + timeout);
            TimeUnit.MILLISECONDS.sleep(timeout);
        } catch (InterruptedException e) {

        }
        return inventoryService.decrementInventory(product, quantity);
    }

    @Override
    public String decrementInventoryError(@PathVariable String product, @PathVariable int quantity) {
        if (quantity > 1) {
            throw new RuntimeException("库存服务不可用");
        }
        return inventoryService.decrementInventory(product, quantity);
    }

    @Override
    public Map<String, Integer> inventoryDetail() {
        Map<String, Integer> map = new HashMap<>();
        inventoryService.findAll().stream().forEach(inventory -> map.put(inventory.getProduct(), inventory.getQuantity()));
        return map;
    }
}
