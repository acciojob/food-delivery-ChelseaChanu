package com.driver.service.impl;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.Converter.OrderConverter;
import com.driver.io.entity.OrderEntity;
import com.driver.io.repository.OrderRepository;
import com.driver.service.OrderService;
import com.driver.shared.dto.OrderDto;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;

    @Override
    public OrderDto createOrder(OrderDto order) {
        OrderEntity orderEntity = OrderConverter.orderDtoToEntity(order);
        orderRepository.save(orderEntity);
        return order;
    }

    @Override
    public OrderDto getOrderById(String orderId) throws Exception {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        OrderDto orderDto = new OrderDto();
        if(orderEntity!=null){
            orderDto = OrderConverter.entityToDto(orderEntity);
        }
        else{
            throw new Exception("Order not found");
        }
        return orderDto;
    }

    @Override
    public OrderDto updateOrderDetails(String orderId, OrderDto order) throws Exception {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        if(orderEntity!=null){
            orderEntity = OrderConverter.orderDtoToEntity(order);
            orderRepository.save(orderEntity);
        }
        else{
            throw new Exception("Order not found");
        }
        return order;
    }

    @Override
    public void deleteOrder(String orderId) throws Exception {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        if(orderEntity!=null){
           orderRepository.delete(orderEntity);
        }
        else{
            throw new Exception("Order not found");
        }
    }

    @Override
    public List<OrderDto> getOrders() {
        List<OrderEntity> orderEntityList = (List<OrderEntity>) orderRepository.findAll();
        List<OrderDto> orderDtoList = new ArrayList<>();
        for(OrderEntity orderEntity:orderEntityList){
            OrderDto orderDto = OrderConverter.entityToDto(orderEntity);
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }

}