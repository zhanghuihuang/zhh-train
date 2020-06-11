package com.zhh.train.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : zhanghuihuang
 * @description : zhh-train
 * <pre>
 * </pre>
 * @since : 2020/6/8 1:27 下午
 */
@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisTemplate<String, Object> template;

    @GetMapping
    public ResponseEntity setKeys() {
        for (int i = 0; i < 30000; i++) {
            template.opsForValue().set(String.valueOf(i), i);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{key}")
    public ResponseEntity getKey(@PathVariable String key) {
        return ResponseEntity.ok(template.opsForValue().get(key));
    }
}
