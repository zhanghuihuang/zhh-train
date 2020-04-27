package com.zhh.train.authorization.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/4/25 11:35 上午
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @GetMapping
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity queryOrderList() {
        return ResponseEntity.ok(new int[]{1, 2, 3, 4, 5});
    }

    @GetMapping("/1")
    @PreAuthorize("hasAuthority('order@order/1@get')")
    public ResponseEntity queryOrderList1() {
        return ResponseEntity.ok(new int[]{6, 7, 8, 9, 0});
    }
}
