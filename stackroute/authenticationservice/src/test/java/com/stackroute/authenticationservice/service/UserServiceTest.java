package com.stackroute.authenticationservice.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.Times;

import com.stackroute.authenticationservice.domain.User;
import com.stackroute.authenticationservice.exception.UserNotFoundException;
import com.stackroute.authenticationservice.repository.UserRepository;

import junit.framework.Assert;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

public class UserServiceTest {

	@Mock
	public UserRepository userRepository;

	public User user;

	@InjectMocks
	public UserServiceImpl userServiceImpl;

	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);

		user = new User();

		user.setUsername("Bhupesh");
		user.setPassword("Bhupesh123");
	}

	@Test
	public void saveUser() {
		Mockito.when(userRepository.save(user)).thenReturn(user);
		User fetchedUser = userServiceImpl.saveUser(user);
		Assert.assertEquals(fetchedUser, user);
		verify(userRepository, times(1)).save(user);

	}

	@Test
	public void verifyUsernameAndPassword() throws UserNotFoundException {
		Mockito.when(userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword())).thenReturn(user);
		User fetchedUser = userServiceImpl.findByUserNameAndPassword(user.getUsername(), user.getPassword());
		Assert.assertEquals(fetchedUser, user);
		verify(userRepository, times(1)).findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}

}
