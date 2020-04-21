package com.zhh.train.inventory.service;

import com.zhh.train.inventory.entity.Inventory;

import java.util.List;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/4/20 2:43 下午
 */
public interface InventoryService {
    String decrementInventory(String product, int quantity);

    List<Inventory> findAll();
}
