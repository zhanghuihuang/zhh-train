package com.zhh.train.order;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @SpringBootApplication = @SpringBootConfiguration + @EnableAutoConfiguration + @ComponentScan
 * @EnableHystrix = @EnableCircuitBreaker
 * @SpringCloudApplication = @SpringBootApplication + @EnableDiscoveryClient + @EnableCircuitBreaker
 */
//@SpringBootApplication
//@EnableDiscoveryClient
//@EnableHystrix
//@EnableCircuitBreaker
@SpringCloudApplication
@EnableFeignClients
@EnableJpaAuditing
public class OrderServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServerApplication.class, args);
    }
}
