package com.zhh.train.order.service.inventory;

import com.zhh.train.inventory.api.service.InventoryV3Api;
import com.zhh.train.order.service.inventory.factory.InventoryV3ApiFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/4/19 5:37 下午
 */
@FeignClient(name = "inventory-server", fallbackFactory = InventoryV3ApiFallbackFactory.class)
public interface InventoryV3ApiClient extends InventoryV3Api {
}
