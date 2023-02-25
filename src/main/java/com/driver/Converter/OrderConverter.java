package com.driver.Converter;

import com.driver.io.entity.OrderEntity;
import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.shared.dto.OrderDto;

public class OrderConverter {
    public static OrderEntity orderDtoToEntity(OrderDto orderDto){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderDto.getId());
        orderEntity.setOrderId(orderDto.getOrderId());
        orderEntity.setCost(orderDto.getCost());
        orderEntity.setItems(orderDto.getItems());
        orderEntity.setUserId(orderDto.getUserId());
        orderEntity.setStatus(orderDto.isStatus());
        return orderEntity;
    }

    public static OrderDetailsResponse orderDtoToResponse(OrderDto orderDto){
        OrderDetailsResponse orderResponse = new OrderDetailsResponse();
        orderResponse.setOrderId(orderDto.getOrderId());
        orderResponse.setCost(orderDto.getCost());
        orderResponse.setItems(orderDto.getItems());
        orderResponse.setUserId(orderDto.getUserId());
        orderResponse.setStatus(orderDto.isStatus());
        return orderResponse;
    }
    
    public static OrderDto requestToDto(OrderDetailsRequestModel orderDetails){
        OrderDto orderDto = new OrderDto();
        orderDto.setCost(orderDetails.getCost());
        orderDto.setItems(orderDetails.getItems());
        orderDto.setUserId(orderDetails.getUserId());
        return orderDto;
    }

    public static OrderDto entityToDto(OrderEntity orderEntity){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(orderEntity.getId());
        orderDto.setOrderId(orderEntity.getOrderId());
        orderDto.setCost(orderEntity.getCost());
        orderDto.setItems(orderEntity.getItems());
        orderDto.setUserId(orderEntity.getUserId());
        orderDto.setStatus(orderEntity.isStatus());
        return orderDto;
    }
}
