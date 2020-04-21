package com.zhh.train.inventory.repository;

import com.zhh.train.inventory.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/4/19 8:19 下午
 */
@Repository
public interface InventoryRepository extends JpaRepository<Inventory, String> {
}
