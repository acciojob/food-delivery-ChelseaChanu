package com.driver.Converter;

import com.driver.io.entity.FoodEntity;
import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.shared.dto.FoodDto;

public class FoodConverter {
    public static FoodEntity foodDtoToEntity(FoodDto foodDto){
        FoodEntity foodEntity = new FoodEntity();
        foodEntity.setId(foodDto.getId());
        foodEntity.setFoodId(foodDto.getFoodId());
        foodEntity.setFoodName(foodDto.getFoodName());
        foodEntity.setFoodPrice(foodDto.getFoodPrice());
        foodEntity.setFoodCategory(foodDto.getFoodCategory());
        return foodEntity;
    }

    public static FoodDetailsResponse foodDtoToResponse(FoodDto foodDto){
        FoodDetailsResponse foodResponse = new FoodDetailsResponse();
        foodResponse.setFoodId(foodDto.getFoodId());
        foodResponse.setFoodName(foodDto.getFoodName());
        foodResponse.setFoodPrice(foodDto.getFoodPrice());
        foodResponse.setFoodCategory(foodDto.getFoodCategory());
        return foodResponse;
    }
    
    public static FoodDto requestToDto(FoodDetailsRequestModel foodDetails){
        FoodDto foodDto = new FoodDto();
        foodDto.setFoodName(foodDetails.getFoodName());
        foodDto.setFoodPrice(foodDetails.getFoodPrice());
        foodDto.setFoodCategory(foodDetails.getFoodCategory());
        return foodDto;
    }

    public static FoodDto entityToDto(FoodEntity foodEntity){
        FoodDto foodDto = new FoodDto();
        foodDto.setId(foodEntity.getId());
        foodDto.setFoodId(foodEntity.getFoodId());
        foodDto.setFoodName(foodEntity.getFoodName());
        foodDto.setFoodPrice(foodEntity.getFoodPrice());
        foodDto.setFoodCategory(foodEntity.getFoodCategory());
        return foodDto;
    }
}
