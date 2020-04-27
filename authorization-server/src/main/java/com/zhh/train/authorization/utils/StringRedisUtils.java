package com.zhh.train.authorization.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/4/27 1:17 下午
 */
@Component
@Lazy
@Slf4j
public class StringRedisUtils {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    public <T> T get(String key, Class<T> tClass) {
        String object = stringRedisTemplate.opsForValue().get(key);
        if (object != null && (object.startsWith("[") || object.startsWith("{"))) {
            try {
                return objectMapper.readValue(object, tClass);
            } catch (JsonProcessingException e) {
                log.info("key={},className={},value={}", key, tClass.getSimpleName(), object);
                log.error("obtain json value by " + key + ",to " + tClass.getSimpleName() + " error.", e);
            }
        }
        return null;
    }

    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void put(String key, Object object) {
        String value = null;
        if (object instanceof String) {
            value = (String) object;
        } else {
            try {
                value = objectMapper.writeValueAsString(object);
            } catch (JsonProcessingException e) {
                log.info("key={},value={}", key, object);
                log.error("set json value by " + key + " error.", e);
            }
        }
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public Set<String> keys(String pattern) {
        return stringRedisTemplate.keys(pattern);
    }

    public int removeByPattern(String pattern) {
        Set<String> keys = stringRedisTemplate.keys(pattern);
        if (keys.size() > 0) {
            stringRedisTemplate.delete(keys);
        }
        return keys.size();
    }
}
