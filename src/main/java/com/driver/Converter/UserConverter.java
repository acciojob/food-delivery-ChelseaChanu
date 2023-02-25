package com.driver.Converter;

import com.driver.io.entity.UserEntity;
import com.driver.model.request.UserDetailsRequestModel;
import com.driver.model.response.UserResponse;
import com.driver.shared.dto.UserDto;

public class UserConverter {
    public static UserEntity userDtoToEntity(UserDto userDto){
        UserEntity orderEntity = new UserEntity();
        orderEntity.setId(userDto.getId());
        orderEntity.setUserId(userDto.getUserId());
        orderEntity.setFirstName(userDto.getFirstName());
        orderEntity.setLastName(userDto.getLastName());
        orderEntity.setEmail(userDto.getEmail());
        return orderEntity;
    }

    public static UserResponse userDtoToResponse(UserDto userDto){
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(userDto.getUserId());
        userResponse.setFirstName(userDto.getFirstName());
        userResponse.setLastName(userDto.getLastName());
        userResponse.setEmail(userDto.getEmail());
        return userResponse;
    }
    
    public static UserDto requestToDto(UserDetailsRequestModel userDetails){
        UserDto userDto = new UserDto();
        userDto.setFirstName(userDetails.getFirstName());
        userDto.setLastName(userDetails.getLastName());
        userDto.setEmail(userDetails.getEmail());
        return userDto;
    }

    public static UserDto entityToDto(UserEntity userEntity){
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setUserId(userEntity.getUserId());
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setLastName(userEntity.getLastName());
        userDto.setEmail(userEntity.getEmail());
        return userDto;
    }
}
