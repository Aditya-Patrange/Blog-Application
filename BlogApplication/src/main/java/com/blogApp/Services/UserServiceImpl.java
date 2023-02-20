package com.blogApp.Services;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogApp.Entities.User;
import com.blogApp.Exceptions.ResourceNotFoundException;
import com.blogApp.Payloads.UserDTO;
import com.blogApp.Repositories.UserRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	
	@Override
	public UserDTO createUser(UserDTO userDto) {
			User user = this.dtoToUser(userDto);
			User savedUser = this.userRepo.save(user);
			
		return this.userToDto(savedUser);
	}

	@Override
	public UserDTO updateUser(UserDTO userDto, Integer userID) {
		User user = this.userRepo.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User","id ",userID));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updateUser = this.userRepo.save(user);
		UserDTO userDto1 =  this.userToDto(updateUser);
		return userDto1;
	}

	@Override
	public UserDTO getUserById(Integer userID) {
		User user = this.userRepo.findById(userID).orElseThrow(()->new ResourceNotFoundException("User", "Id" ,userID));
		return this.userToDto(user);
	}

	@Override
	public List<UserDTO> getAllUser() {
		List<User> users =  this.userRepo.findAll();
		List<UserDTO> userDtos = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public boolean deleteUser(int userID) {
		User user = this.userRepo.findById(userID).orElseThrow(()->new ResourceNotFoundException("User", "Id", userID));
		this.userRepo.delete(user);
		return true;
	}
	
	
	
//	DTO to Entity
	public User dtoToUser(UserDTO userDto) {
		User user = new User();
		user.setUserId(userDto.getUserId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		return user;
	}

//	without using model mapper manual conversion
//	Entity to DTO
	public UserDTO userToDto(User user) {
		UserDTO userDto = new UserDTO();
		userDto.setUserId(user.getUserId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setAbout(user.getAbout());
		return userDto;
	}

}
