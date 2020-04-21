package com.zhh.train.inventory.service.impl;

import com.zhh.train.inventory.entity.Inventory;
import com.zhh.train.inventory.repository.InventoryRepository;
import com.zhh.train.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/4/20 2:44 下午
 */
@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public String decrementInventory(String product, int quantity) {
        Inventory inventory = inventoryRepository.findById(product).orElse(null);
        if (inventory == null) {
            throw new RuntimeException("cannot found inventory " + product);
        }
        int endQuantity = inventory.getQuantity() - quantity;
        if (endQuantity < 0) {
            throw new RuntimeException("inventory num shortage.");
        }
        inventory.setQuantity(endQuantity);
        inventoryRepository.save(inventory);
        return String.format("product[%s] current inventory qty:%d.", inventory.getProduct(), inventory.getQuantity());
    }

    @Override
    public List<Inventory> findAll() {
        return inventoryRepository.findAll();
    }
}
