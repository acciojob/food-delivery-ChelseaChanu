package com.driver.ui.controller;

import java.util.List;
import java.util.ArrayList;

import com.driver.Converter.FoodConverter;
import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.RequestOperationName;
import com.driver.model.response.RequestOperationStatus;
import com.driver.service.FoodService;
import com.driver.shared.dto.FoodDto;

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
@RequestMapping("/foods")
public class FoodController {

	@Autowired
	FoodService foodService;

	@GetMapping(path="/{id}")
	public FoodDetailsResponse getFood(@PathVariable String id) throws Exception{
		FoodDto foodDto = foodService.getFoodById(id);
		FoodDetailsResponse food = FoodConverter.foodDtoToResponse(foodDto);
		return food;
	}

	@PostMapping("/create")
	public FoodDetailsResponse createFood(@RequestBody FoodDetailsRequestModel foodDetails) {
		FoodDto foodDto = FoodConverter.requestToDto(foodDetails);
		FoodDto responseDto = foodService.createFood(foodDto);
		FoodDetailsResponse response = FoodConverter.foodDtoToResponse(responseDto);
		return response;
	}

	@PutMapping(path="/{id}")
	public FoodDetailsResponse updateFood(@PathVariable String id, @RequestBody FoodDetailsRequestModel foodDetails) throws Exception{
		FoodDto foodDto = FoodConverter.requestToDto(foodDetails);
		FoodDto foodDtoResponse = foodService.updateFoodDetails(id, foodDto);
		FoodDetailsResponse response = FoodConverter.foodDtoToResponse(foodDtoResponse);
		return response;
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteFood(@PathVariable String id) throws Exception{
		OperationStatusModel res = new OperationStatusModel();
		try{
			foodService.deleteFoodItem(id);
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
	public List<FoodDetailsResponse> getFoods() {
		List<FoodDto> foodDtoList = foodService.getFoods();
		List<FoodDetailsResponse> foodResponseList = new ArrayList<>();
		for(FoodDto foodDto:foodDtoList){
			FoodDetailsResponse foodResponse = FoodConverter.foodDtoToResponse(foodDto);
			foodResponseList.add(foodResponse);
		}
		return foodResponseList;
	}
}
