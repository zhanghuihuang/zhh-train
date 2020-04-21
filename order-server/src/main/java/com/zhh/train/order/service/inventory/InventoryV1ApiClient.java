package com.zhh.train.order.service.inventory;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zhh.train.inventory.api.InventoryServiceURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author : page
 * @project : zhh-train
 * @description : //TODO
 * @date : 2020/4/11 7:13 下午
 */
@Service("inventoryV1ApiClient")
public class InventoryV1ApiClient {

    @Autowired
    private RestTemplate restTemplate;

    public String decrementInventory(String product, int quantity) {
        restTemplate.exchange(InventoryServiceURL.getInventoryPrefix() + InventoryServiceURL.getInventoryPath(product, quantity), HttpMethod.PUT, HttpEntity.EMPTY, String.class);
        return "success";
    }

    /**
     * execution.isolation.thread.timeoutInMilliseconds 配置调用超时2000毫秒,则执行fallback
     *
     * @param product
     * @param quantity
     */
    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            }
    )
    public String decrementInventoryTimeout(String product, int quantity) {
        System.out.println("调用扣减库存(超时方法)");
        restTemplate.exchange(InventoryServiceURL.getInventoryPrefix() + InventoryServiceURL.getInventoryTimeoutPath(product, quantity), HttpMethod.PUT, HttpEntity.EMPTY, String.class);
        return "success";
    }

    /**
     * 模拟库存服务不可用时,启用备用方案
     *
     * @param product
     * @param quantity
     * @return
     */
    @HystrixCommand(
            fallbackMethod = "defaultDecrementInventoryFallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            }
    )
    public String decrementInventoryFallback(String product, int quantity) {
        System.out.println("调用扣减库存不可用");
        restTemplate.exchange(InventoryServiceURL.getInventoryPrefix() + InventoryServiceURL.getInventoryErrorPath(product, quantity), HttpMethod.PUT, HttpEntity.EMPTY, String.class);
        return "success";
    }

    public String defaultDecrementInventoryFallback(String product, int quantity) {
        System.out.println("扣减库存超时,执行降级方法");
        return "fail";
    }

    @HystrixCommand(
            threadPoolKey = "decrementInventoryCoreSize",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "2")
            },
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            }
    )
    public String decrementInventoryCoreSize(String product, int quantity) {
        restTemplate.exchange(InventoryServiceURL.getInventoryPrefix() + InventoryServiceURL.getInventoryPath(product, quantity), HttpMethod.PUT, HttpEntity.EMPTY, String.class);
        return "success";
    }

    /**
     * 3秒内,请求次数达到两次,且失败率在50%以上的打开断路器
     * 跳闸后,3秒内要是有一个请求通过的,关闭断路器
     * metrics.rollingStats.timeInMilliseconds 设置统计窗口,默认10s
     * circuitBreaker.requestVolumeThreshold 窗口时间内最小请求次数,默认20次
     * circuitBreaker.errorThresholdPercentage 窗口时间内请求失败率,默认
     * circuitBreaker.sleepWindowInMilliseconds 跳闸后尝试请求时间窗口,默认5s
     *
     * @param product
     * @param quantity
     * @return
     */
    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "3000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "3000"),
            }
    )
    public String decrementInventoryCircuitBreaker(String product, int quantity) {
        ResponseEntity<String> exchange = restTemplate.exchange(InventoryServiceURL.getInventoryPrefix() + InventoryServiceURL.getInventoryErrorPath(product, quantity), HttpMethod.PUT, HttpEntity.EMPTY, String.class);
        return exchange.getBody();
    }

    /**
     * 3秒内,请求次数达到两次,且失败率在50%以上的打开断路器
     * 跳闸后,3秒内要是有一个请求通过的,关闭断路器
     * metrics.rollingStats.timeInMilliseconds 设置统计窗口,默认10s
     * circuitBreaker.requestVolumeThreshold 窗口时间内最小请求次数,默认20次
     * circuitBreaker.errorThresholdPercentage 窗口时间内请求失败率,默认
     * circuitBreaker.sleepWindowInMilliseconds 跳闸后尝试请求时间窗口,默认5s
     *
     * @param product
     * @param quantity
     * @return
     */
    @HystrixCommand(
            fallbackMethod = "defaultDecrementInventoryFallback",
            commandProperties = {
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "3000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "3000"),
            }
    )
    public String decrementInventoryCircuitBreakerFallback(String product, int quantity) {
        ResponseEntity<String> exchange = restTemplate.exchange(InventoryServiceURL.getInventoryPrefix() + InventoryServiceURL.getInventoryErrorPath(product, quantity), HttpMethod.PUT, HttpEntity.EMPTY, String.class);
        return exchange.getBody();
    }

    public Map<String, Integer> inventoryDetail() {
        return restTemplate.getForObject(InventoryServiceURL.getInventoryPrefix(), Map.class);
    }
}
