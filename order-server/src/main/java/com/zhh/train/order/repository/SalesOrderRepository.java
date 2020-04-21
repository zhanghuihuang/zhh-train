package com.zhh.train.order.repository;

import com.zhh.train.order.entity.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/4/19 8:20 下午
 */
@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrder, Integer> {
}
