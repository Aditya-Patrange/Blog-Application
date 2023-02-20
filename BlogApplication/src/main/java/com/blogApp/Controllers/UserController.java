package com.blogApp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogApp.Payloads.UserDTO;
import com.blogApp.Services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/getUsers")
	public ResponseEntity<?> getAllUsers(){
		return ResponseEntity.ok(userService.getAllUser()); 
	}
	
	@GetMapping("/getUser/{id}")
	public ResponseEntity<?> getUserById(@PathVariable int id){
		return ResponseEntity.ok(userService.getUserById(id));
	}
	
	
	@PostMapping("/addUser")
	public ResponseEntity<?> addNewUser(@RequestBody UserDTO userDto){
		return ResponseEntity.ok(userService.createUser(userDto));
	}
	
	
	@PutMapping("/updateUser/{id}")
	public ResponseEntity<?> updateUser(@RequestBody UserDTO userDto, @PathVariable int id){
		return ResponseEntity.ok(userService.updateUser(userDto, id));
	}
	
	
	
	@DeleteMapping("/deleteUser/{id}")
	public boolean deleteUser(@PathVariable ("id") int id) {
		 return userService.deleteUser(id);
		 
	}
	
}
