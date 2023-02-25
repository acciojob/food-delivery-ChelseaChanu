package com.driver.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.Converter.UserConverter;
import com.driver.io.entity.UserEntity;
import com.driver.io.repository.UserRepository;
import com.driver.service.UserService;
import com.driver.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto user) throws Exception {
        UserEntity userEntity = UserConverter.userDtoToEntity(user);
        userRepository.save(userEntity);
        return user;
    }

    @Override
    public UserDto getUser(String email) throws Exception {
        UserEntity userEntity = userRepository.findByEmail(email);
        UserDto userDto = new UserDto();
        if(userEntity!=null){
            userDto = UserConverter.entityToDto(userEntity);
        }
        else{
            throw new Exception("Order not found");
        }
        return userDto;
    }

    @Override
    public UserDto getUserByUserId(String userId) throws Exception {
        UserEntity userEntity = userRepository.findByUserId(userId);
        UserDto userDto = new UserDto();
        if(userEntity!=null){
            userDto = UserConverter.entityToDto(userEntity);
        }
        else{
            throw new Exception("Order not found");
        }
        return userDto;
    }

    @Override
    public UserDto updateUser(String userId, UserDto user) throws Exception {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if(userEntity!=null){
            userEntity = UserConverter.userDtoToEntity(user);
            userRepository.save(userEntity);
        }
        else{
            throw new Exception("Order not found");
        }
        return user;
    }

    @Override
    public void deleteUser(String userId) throws Exception {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if(userEntity!=null){
            userRepository.delete(userEntity);
        }
        else{
            throw new Exception("User not found");
        }
    }

    @Override
    public List<UserDto> getUsers() {
        List<UserEntity> userEntityList = (List<UserEntity>) userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for(UserEntity userEntity:userEntityList){
            UserDto userDto = UserConverter.entityToDto(userEntity);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

}