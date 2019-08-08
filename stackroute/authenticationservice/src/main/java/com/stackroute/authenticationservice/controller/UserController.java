package com.stackroute.authenticationservice.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.authenticationservice.domain.User;
import com.stackroute.authenticationservice.exception.UserNotFoundException;
import com.stackroute.authenticationservice.service.SecurityTokenGenerator;
import com.stackroute.authenticationservice.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/userservice")
public class UserController {
	
	private ResponseEntity responseEntity;
	
	private UserService userService;
	private SecurityTokenGenerator secTokenGenerator;
	
	
	public UserController(UserService userService, SecurityTokenGenerator secTokenGenerator) {
		super();
		this.userService = userService;
		this.secTokenGenerator = secTokenGenerator;
	}
	
	@RequestMapping(method=RequestMethod.POST, path="/save")
	public ResponseEntity saveUser(@RequestBody User user) {
		System.out.println("user info in User  Controller" +user.toString());
		
		userService.saveUser(user);
		return new ResponseEntity(user, HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.POST, path="/login")
	public ResponseEntity loginUser(@RequestBody User user) throws UserNotFoundException {
		
		Map<String, String> 	map =null;
		try {
			User userObj = userService.findByUserNameAndPassword(user.getUsername(), user.getPassword());
			
			if(userObj.getUsername().equals(user.getUsername())) {
				map = secTokenGenerator.generateToken(userObj);
			}
			responseEntity = new ResponseEntity(map, HttpStatus.OK);
			
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			throw new UserNotFoundException();
		} catch (Exception e) {
			responseEntity = new ResponseEntity("try after some time", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}


}
