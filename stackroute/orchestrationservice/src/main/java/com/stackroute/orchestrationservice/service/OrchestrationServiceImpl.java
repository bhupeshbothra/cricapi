package com.stackroute.orchestrationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.stackroute.orchestrationservice.domain.User;
import com.stackroute.orchestrationservice.exception.UserAlreadyExistsException;

@Service
public class OrchestrationServiceImpl implements OrchestrationService {

	@Autowired
	RestTemplate restTemplate;
	
	String urlUserTrackService = "http://usertrackservice/api/v1/usertrackservice/register";
	String urlAuthenticationService = "http://authenticationservice/api/v1/userservice/save";
	@Override
	public User userRegistration(User user) throws UserAlreadyExistsException {
		// TODO Auto-generated method stub
		User userResponse =null;
		
		try {
			userResponse = restTemplate.postForObject(urlUserTrackService, user, User.class);
			restTemplate.postForObject(urlAuthenticationService, user, User.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserAlreadyExistsException();
		}
		return userResponse;
	}

}
