package com.driver.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.Converter.FoodConverter;
import com.driver.io.entity.FoodEntity;
import com.driver.io.repository.FoodRepository;
import com.driver.service.FoodService;
import com.driver.shared.dto.FoodDto;

@Service
public class FoodServiceImpl implements FoodService{

    @Autowired
    FoodRepository foodRepository;

    @Override
    public FoodDto createFood(FoodDto food) {
        FoodEntity foodEntity = FoodConverter.foodDtoToEntity(food);
        foodRepository.save(foodEntity);
        return food;
    }

    @Override
    public FoodDto getFoodById(String foodId) throws Exception {
        FoodEntity food = foodRepository.findByFoodId(foodId);
        FoodDto foodDto = new FoodDto();
        if(food!=null){
            foodDto = FoodConverter.entityToDto(food);
        }
        else{
            throw new Exception("Food not found");
        }
        return foodDto;
    }

    @Override
    public FoodDto updateFoodDetails(String foodId, FoodDto foodDetails) throws Exception {
        FoodEntity foodEntity = foodRepository.findByFoodId(foodId);
        if(foodEntity!=null){
            foodEntity = FoodConverter.foodDtoToEntity(foodDetails);
            foodRepository.save(foodEntity);
        }
        else{
            throw new Exception("Food not found");
        }
        return foodDetails;
    }

    @Override
    public void deleteFoodItem(String id) throws Exception {
        FoodEntity foodEntity = foodRepository.findByFoodId(id);
        if(foodEntity!=null){
            foodRepository.delete(foodEntity);
        }
        else{
            throw new Exception("Food not found");
        }
    }

    @Override
    public List<FoodDto> getFoods() {
        List<FoodEntity> foodEntityList = (List<FoodEntity>) foodRepository.findAll();
        List<FoodDto> foodDtoList = new ArrayList<>();
        for(FoodEntity foodEntity:foodEntityList){
            FoodDto foodDto = FoodConverter.entityToDto(foodEntity);
            foodDtoList.add(foodDto);
        }
        return foodDtoList;
    }

}