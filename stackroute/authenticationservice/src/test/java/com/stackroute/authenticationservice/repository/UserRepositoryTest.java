package com.stackroute.authenticationservice.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.authenticationservice.domain.User;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	private User user;

	@Before
	public void setup() {
		user = new User();
		user.setUsername("Bhupesh");
		user.setPassword("Bhupesh123");
	}

	@After
	public void delete() {
		userRepository.deleteAll();
		user = null;
	}

	@Test
	public void saveUser() {
		userRepository.save(user);
		User userObj = userRepository.findById(user.getUserId()).get();
		Assert.assertEquals(userObj.getUsername(), user.getUsername());
		userRepository.delete(user);
	}

	@Test
	public void findUserLoginStatus() {
		userRepository.save(user);
		User userObj = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		Assert.assertEquals(userObj.getUsername(), user.getUsername());
		userRepository.delete(user);
	}

}
