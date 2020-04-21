package com.zhh.train.order.service.impl;

import com.zhh.train.order.dto.SalesOrderDto;
import com.zhh.train.order.entity.SalesOrder;
import com.zhh.train.order.repository.SalesOrderRepository;
import com.zhh.train.order.service.SalesOrderService;
import com.zhh.train.order.service.inventory.InventoryV2ApiClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/4/20 12:36 下午
 */
@Service("salesOrderV2Service")
@Transactional
public class SalesOrderV2ServiceImpl implements SalesOrderService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private InventoryV2ApiClient inventoryV2ApiClient;
    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @Override
    public SalesOrder buyGoods(SalesOrderDto salesOrderDto) {
        SalesOrder salesOrder = saveSalesOrder(salesOrderDto);
        //请求库存,扣库存数量
        String s = inventoryV2ApiClient.decrementInventory(salesOrder.getProduct(), salesOrder.getQuantity());
        salesOrder.setMessage(s);
        return salesOrder;
    }

    @Override
    public SalesOrder buyGoodsTimeout(SalesOrderDto salesOrderDto) {
        SalesOrder salesOrder = saveSalesOrder(salesOrderDto);
        //请求库存,扣库存数量
        String s = inventoryV2ApiClient.decrementInventoryTimeout(salesOrder.getProduct(), salesOrder.getQuantity());
        salesOrder.setMessage(s);
        return salesOrder;
    }

    @Override
    public SalesOrder buyGoodsError(SalesOrderDto salesOrderDto) {
        SalesOrder salesOrder = saveSalesOrder(salesOrderDto);
        //请求库存,扣库存数量
        String s = inventoryV2ApiClient.decrementInventoryFallback(salesOrder.getProduct(), salesOrder.getQuantity());
        salesOrder.setMessage(s);
        return salesOrder;
    }

    @Override
    public SalesOrder buyGoodsCoreSize(SalesOrderDto salesOrderDto) {
        SalesOrder salesOrder = saveSalesOrder(salesOrderDto);
        //请求库存,扣库存数量
        String s = inventoryV2ApiClient.decrementInventoryCoreSize(salesOrder.getProduct(), salesOrder.getQuantity());
        salesOrder.setMessage(s);
        return salesOrder;
    }

    @Override
    public SalesOrder buyGoodsCircuitBreaker(SalesOrderDto salesOrderDto) {
        SalesOrder salesOrder = saveSalesOrder(salesOrderDto);
        //请求库存,扣库存数量
        String s = inventoryV2ApiClient.decrementInventoryCircuitBreaker(salesOrder.getProduct(), salesOrder.getQuantity());
        salesOrder.setMessage(s);
        return salesOrder;
    }

    @Override
    public SalesOrder buyGoodsCircuitBreakerFallback(SalesOrderDto salesOrderDto) {
        SalesOrder salesOrder = saveSalesOrder(salesOrderDto);
        //请求库存,扣库存数量
        String s = inventoryV2ApiClient.decrementInventoryCircuitBreakerFallback(salesOrder.getProduct(), salesOrder.getQuantity());
        salesOrder.setMessage(s);
        return salesOrder;
    }

    @Override
    public SalesOrder loadSalesOrder(Integer orderNo) {
        return salesOrderRepository.findById(orderNo).orElse(null);
    }

    @Override
    public List<SalesOrder> findAll() {
        return salesOrderRepository.findAll();
    }

    private SalesOrder saveSalesOrder(SalesOrderDto salesOrderDto) {
        SalesOrder salesOrder = modelMapper.map(salesOrderDto, SalesOrder.class);
        salesOrderRepository.save(salesOrder);
        salesOrderDto.setId(salesOrder.getOrderNo());
        return salesOrder;
    }
}
