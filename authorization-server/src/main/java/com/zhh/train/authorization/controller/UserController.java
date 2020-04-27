package com.zhh.train.authorization.controller;

import com.zhh.train.authorization.dto.UserSaveDto;
import com.zhh.train.authorization.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/4/26 10:38 上午
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    @PreAuthorize("hasAuthority('user@user@post')")
    public ResponseEntity<Long> save(UserSaveDto dto) {
        return ResponseEntity.ok(userService.createUser(dto));
    }
}