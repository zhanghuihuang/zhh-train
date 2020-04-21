package com.zhh.train.order.service.impl;

import com.zhh.train.order.dto.SalesOrderDto;
import com.zhh.train.order.entity.SalesOrder;
import com.zhh.train.order.repository.SalesOrderRepository;
import com.zhh.train.order.service.SalesOrderService;
import com.zhh.train.order.service.inventory.InventoryV3ApiClient;
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
@Service("salesOrderV3Service")
@Transactional
public class SalesOrderV3ServiceImpl implements SalesOrderService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private InventoryV3ApiClient inventoryService;
    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @Override
    public SalesOrder buyGoods(SalesOrderDto salesOrderDto) {
        SalesOrder salesOrder = saveSalesOrder(salesOrderDto);
        //请求库存,扣库存数量
        String s = inventoryService.decrementInventory(salesOrder.getProduct(), salesOrder.getQuantity());
        salesOrder.setMessage(s);
        return salesOrder;
    }

    @Override
    public SalesOrder buyGoodsTimeout(SalesOrderDto salesOrderDto) {
        SalesOrder salesOrder = saveSalesOrder(salesOrderDto);
        //请求库存,扣库存数量
        String s = inventoryService.decrementInventoryTimeout(salesOrder.getProduct(), salesOrder.getQuantity());
        salesOrder.setMessage(s);
        return salesOrder;
    }

    @Override
    public SalesOrder buyGoodsError(SalesOrderDto salesOrderDto) {
        SalesOrder salesOrder = saveSalesOrder(salesOrderDto);
        //请求库存,扣库存数量
        String s = inventoryService.decrementInventoryError(salesOrder.getProduct(), salesOrder.getQuantity());
        salesOrder.setMessage(s);
        return salesOrder;
    }

    @Override
    public SalesOrder buyGoodsCoreSize(SalesOrderDto salesOrderDto) {
        SalesOrder salesOrder = saveSalesOrder(salesOrderDto);
        //请求库存,扣库存数量
        String s = inventoryService.decrementInventoryTimeout(salesOrder.getProduct(), salesOrder.getQuantity());
        salesOrder.setMessage(s);
        return salesOrder;
    }

    @Override
    public SalesOrder buyGoodsCircuitBreaker(SalesOrderDto salesOrderDto) {
        SalesOrder salesOrder = saveSalesOrder(salesOrderDto);
        //请求库存,扣库存数量
        String s = inventoryService.decrementInventoryError(salesOrder.getProduct(), salesOrder.getQuantity());
        salesOrder.setMessage(s);
        return salesOrder;
    }

    @Override
    public SalesOrder buyGoodsCircuitBreakerFallback(SalesOrderDto salesOrderDto) {
        SalesOrder salesOrder = saveSalesOrder(salesOrderDto);
        //请求库存,扣库存数量
        String s = inventoryService.decrementInventoryError(salesOrder.getProduct(), salesOrder.getQuantity());
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

    private SalesOrder defaultDecrementInventoryFallback(SalesOrderDto salesOrderDto) {
        SalesOrder salesOrder = saveSalesOrder(salesOrderDto);
        salesOrder.setMessage("库存调用失败,降级,记录库存帐:-" + salesOrderDto.getQuantity());
        return salesOrder;
    }

    private SalesOrder saveSalesOrder(SalesOrderDto salesOrderDto) {
        SalesOrder salesOrder = modelMapper.map(salesOrderDto, SalesOrder.class);
        salesOrderRepository.save(salesOrder);
        salesOrderDto.setId(salesOrder.getOrderNo());
        return salesOrder;
    }
}
