package com.zhh.train.inventory.api.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author : page
 * @project : zhh-train
 * @description : //TODO
 * @date : 2020/4/11 7:13 下午
 */
@RequestMapping("/inventory")
public interface InventoryV3Api {

    @PutMapping("/{product}/{quantity}")
    String decrementInventory(@PathVariable String product, @PathVariable int quantity);

    @PutMapping("/timeout/{product}/{quantity}")
    String decrementInventoryTimeout(@PathVariable String product, @PathVariable int quantity);

    @PutMapping("/error/{product}/{quantity}")
    String decrementInventoryError(@PathVariable String product, @PathVariable int quantity);

    @GetMapping
    Map<String, Integer> inventoryDetail();
}
