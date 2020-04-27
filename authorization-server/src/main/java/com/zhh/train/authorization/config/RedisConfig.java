package com.zhh.train.authorization.config;

import com.zhh.train.authorization.service.impl.JsonSerializationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/4/27 12:24 下午
 */
@Configuration
public class RedisConfig {

    @Autowired
    private JsonSerializationStrategy jsonSerializationStrategy;

    @Bean
    public RedisTokenStore redisTokenStore(RedisConnectionFactory redisConnectionFactory) {
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        redisTokenStore.setSerializationStrategy(jsonSerializationStrategy);
        return redisTokenStore;
    }
}
