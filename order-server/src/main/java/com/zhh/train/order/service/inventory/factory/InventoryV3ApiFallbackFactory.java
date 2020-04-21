package com.zhh.train.order.service.inventory.factory;

import com.zhh.train.order.service.inventory.InventoryV3ApiClient;
import feign.hystrix.FallbackFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/4/20 5:31 下午
 */
public class InventoryV3ApiFallbackFactory implements FallbackFactory {
    @Override
    public InventoryV3ApiClient create(Throwable throwable) {
        InventoryV3ApiClient inventoryV3ApiClient = new InventoryV3ApiClient() {
            @Override
            public String decrementInventory(String product, int quantity) {
                return "调用库存服务失败,进行降级处理:" + product + "," + quantity;
            }

            @Override
            public String decrementInventoryTimeout(String product, int quantity) {
                return "调用库存服务失败,进行降级处理:" + product + "," + quantity;
            }

            @Override
            public String decrementInventoryError(String product, int quantity) {
                return "调用库存服务失败,进行降级处理:" + product + "," + quantity;
            }

            @Override
            public Map<String, Integer> inventoryDetail() {
                return new HashMap<>();
            }
        };
        return inventoryV3ApiClient;
    }
}
