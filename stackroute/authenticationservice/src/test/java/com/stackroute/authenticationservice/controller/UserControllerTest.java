package com.stackroute.authenticationservice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.authenticationservice.domain.User;
import com.stackroute.authenticationservice.service.SecurityTokenGenerator;
import com.stackroute.authenticationservice.service.UserService;

import ch.qos.logback.core.status.Status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@MockBean
	private SecurityTokenGenerator securityTokenGenerator;

	private User user;

	@InjectMocks
	private UserController userController;

	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);

		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
		user = new User();

		user.setUsername("Bhupesh");
		user.setPassword("Bothra");
	}

	@Test
	public void testSaveUser() throws Exception {
		when(userService.saveUser(any())).thenReturn(user);
		mockMvc.perform(
				post("/api/v1/userservice/save").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
				.andExpect(status().isCreated()).andDo(print());

		verify(userService, times(1)).saveUser(any());

	}

	@Test
	public void testUserPassword() throws Exception { //
		when(userService.saveUser(any())).thenReturn(user);
		when(userService.findByUserNameAndPassword(user.getUsername(), user.getPassword())).thenReturn(user);
		mockMvc.perform(
				post("/api/v1/userservice/login").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
				.andExpect(status().isOk()).andDo(print());

		verify(userService, times(1)).findByUserNameAndPassword(user.getUsername(), user.getPassword());

	}

	private static String jsonToString(final Object obj) throws Exception {
		String result;

		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			result = jsonContent;
		} catch (Exception e) {
			result = "error in Json processing";
		}
		return result;
	}

}
