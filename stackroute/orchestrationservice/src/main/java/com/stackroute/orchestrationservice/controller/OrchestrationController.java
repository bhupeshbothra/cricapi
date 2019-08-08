package com.stackroute.orchestrationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.stackroute.orchestrationservice.domain.User;
import com.stackroute.orchestrationservice.exception.UserAlreadyExistsException;
import com.stackroute.orchestrationservice.service.OrchestrationService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class OrchestrationController {

	@Autowired
	OrchestrationService orchestrationService;

	public OrchestrationController(OrchestrationService orchestrationService) {
		super();
		this.orchestrationService = orchestrationService;
	}
	
	@PostMapping("/user")
	public ResponseEntity<?> registerAndSave(@RequestBody User user) throws UserAlreadyExistsException{
		
		ResponseEntity<?> responseEntity = null;
		
		try {
			System.out.println(user.toString());
			User userobj = orchestrationService.userRegistration(user);
			responseEntity = new ResponseEntity(userobj,HttpStatus.CREATED);
		}
		catch(Exception e) {
			throw new UserAlreadyExistsException();
		}
		
		return responseEntity;
	}
	
	
	
}
