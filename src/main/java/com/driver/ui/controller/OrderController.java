package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.driver.Converter.OrderConverter;
import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.model.response.RequestOperationName;
import com.driver.model.response.RequestOperationStatus;
import com.driver.service.OrderService;
import com.driver.shared.dto.OrderDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@GetMapping(path="/{id}")
	public OrderDetailsResponse getOrder(@PathVariable String id) throws Exception{
		OrderDto orderDto = orderService.getOrderById(id);
		OrderDetailsResponse orderResponse = OrderConverter.orderDtoToResponse(orderDto);
		return orderResponse;
	}
	
	@PostMapping()
	public OrderDetailsResponse createOrder(@RequestBody OrderDetailsRequestModel order) {
		OrderDto orderDto = OrderConverter.requestToDto(order);
		orderDto.setOrderId(UUID.randomUUID().toString());
		OrderDto dtoResponse = orderService.createOrder(orderDto);
		OrderDetailsResponse orderResponse = OrderConverter.orderDtoToResponse(dtoResponse);
		return orderResponse;
	}
		
	@PutMapping(path="/{id}")
	public OrderDetailsResponse updateOrder(@PathVariable String id, @RequestBody OrderDetailsRequestModel order) throws Exception{
		OrderDto orderDto = OrderConverter.requestToDto(order);
		OrderDto dtoResponse = orderService.updateOrderDetails(id, orderDto);
		OrderDetailsResponse orderResponse = OrderConverter.orderDtoToResponse(dtoResponse);
		return orderResponse;
	}
	
	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteOrder(@PathVariable String id) throws Exception {
		OperationStatusModel res = new OperationStatusModel();
		try{
			orderService.deleteOrder(id);
			res.setOperationResult(RequestOperationStatus.SUCCESS.toString());
			res.setOperationName(RequestOperationName.DELETE.toString());
		}
		catch(Exception e){
			res.setOperationResult(RequestOperationStatus.ERROR.toString());
			res.setOperationName(RequestOperationName.DELETE.toString());
		}
		return res;
	}
	
	@GetMapping()
	public List<OrderDetailsResponse> getOrders() {
		List<OrderDto> orderDtoList = orderService.getOrders();
		List<OrderDetailsResponse> orderResponseList = new ArrayList<>();
		for(OrderDto orderDto:orderDtoList){
			OrderDetailsResponse orderResponse = OrderConverter.orderDtoToResponse(orderDto);
			orderResponseList.add(orderResponse);
		}
		return orderResponseList;
	}
}
