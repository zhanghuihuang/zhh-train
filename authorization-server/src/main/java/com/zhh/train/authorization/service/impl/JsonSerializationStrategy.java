package com.zhh.train.authorization.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.store.redis.StandardStringSerializationStrategy;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/4/27 2:00 下午
 */
@Service
public class JsonSerializationStrategy extends StandardStringSerializationStrategy {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected <T> T deserializeInternal(byte[] bytes, Class<T> clazz) {
        try {
            return objectMapper.readValue(bytes, clazz);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected byte[] serializeInternal(Object object) {
        try {
            return objectMapper.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            return new byte[0];
        }
    }
}
