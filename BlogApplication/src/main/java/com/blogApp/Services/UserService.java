package com.blogApp.Services;

import java.util.List;

import com.blogApp.Payloads.UserDTO;

public interface UserService {

	UserDTO createUser(UserDTO user);
	UserDTO updateUser(UserDTO user,Integer userID);
	UserDTO getUserById(Integer userID);
	List<UserDTO> getAllUser();
	public boolean deleteUser(int userID);
}
