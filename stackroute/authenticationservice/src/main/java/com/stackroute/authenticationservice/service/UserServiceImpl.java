package com.stackroute.authenticationservice.service;

import org.springframework.stereotype.Service;

import com.stackroute.authenticationservice.domain.User;
import com.stackroute.authenticationservice.exception.UserNotFoundException;
import com.stackroute.authenticationservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public User findByUserNameAndPassword(String userName, String password) throws UserNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByUsernameAndPassword(userName, password);
		
		if (user == null) {
			throw new UserNotFoundException();
		}
		
		return user;
	}

}
