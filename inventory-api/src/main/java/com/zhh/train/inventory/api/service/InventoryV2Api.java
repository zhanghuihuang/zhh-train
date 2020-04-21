package com.zhh.train.inventory.api.service;

import com.zhh.train.inventory.api.InventoryServiceURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author : page
 * @project : zhh-train
 * @description : //TODO
 * @date : 2020/4/11 7:13 下午
 */
@Service("inventoryServiceV2")
public class InventoryV2Api {

    @Autowired
    private RestTemplate restTemplate;

    public String decrementInventory(String product, int quantity) {
        restTemplate.exchange(InventoryServiceURL.getInventoryPrefix() + InventoryServiceURL.getInventoryPath(product, quantity), HttpMethod.PUT, HttpEntity.EMPTY, String.class);
        return "success";
    }

    public String decrementInventoryTimeout(String product, int quantity) {
        System.out.println("调用扣减库存(超时方法)");
        restTemplate.exchange(InventoryServiceURL.getInventoryPrefix() + InventoryServiceURL.getInventoryTimeoutPath(product, quantity), HttpMethod.PUT, HttpEntity.EMPTY, String.class);
        return "success";
    }

    public String decrementInventoryError(String product, int quantity) {
        System.out.println("调用扣减库存不可用");
        restTemplate.exchange(InventoryServiceURL.getInventoryPrefix() + InventoryServiceURL.getInventoryErrorPath(product, quantity), HttpMethod.PUT, HttpEntity.EMPTY, String.class);
        return "success";
    }

    public Map<String, Integer> inventoryDetail() {
        return restTemplate.getForObject(InventoryServiceURL.getInventoryPrefix(), Map.class);
    }
}
