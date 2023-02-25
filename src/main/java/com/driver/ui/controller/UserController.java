package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.driver.Converter.UserConverter;
import com.driver.model.request.UserDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.RequestOperationName;
import com.driver.model.response.RequestOperationStatus;
import com.driver.model.response.UserResponse;
import com.driver.service.UserService;
import com.driver.shared.dto.UserDto;

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
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(path = "/{id}")
	public UserResponse getUser(@PathVariable String id) throws Exception{
		UserDto userDto = userService.getUser(id);
		UserResponse userResponse = UserConverter.userDtoToResponse(userDto);
		return userResponse;
	}

	@PostMapping()
	public UserResponse createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception{
		UserDto userDto = UserConverter.requestToDto(userDetails);
		userDto.setUserId(UUID.randomUUID().toString());
		UserDto dtoResponse = userService.createUser(userDto);
		UserResponse userResponse = UserConverter.userDtoToResponse(dtoResponse);
		return userResponse;
	}

	@PutMapping(path = "/{id}")
	public UserResponse updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) throws Exception{
		UserDto userDto = UserConverter.requestToDto(userDetails);
		UserDto dtoResponse = userService.updateUser(id, userDto);
		UserResponse userResponse = UserConverter.userDtoToResponse(dtoResponse);
		return userResponse;
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteUser(@PathVariable String id) throws Exception{
		OperationStatusModel res = new OperationStatusModel();
		try{
			userService.deleteUser(id);
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
	public List<UserResponse> getUsers(){
		List<UserDto> userDtoList = userService.getUsers();
		List<UserResponse> userResponseList = new ArrayList<>();
		for(UserDto userDto:userDtoList){
			UserResponse userResponse = UserConverter.userDtoToResponse(userDto);
			userResponseList.add(userResponse);
		}
		return userResponseList;
	}
	
}
