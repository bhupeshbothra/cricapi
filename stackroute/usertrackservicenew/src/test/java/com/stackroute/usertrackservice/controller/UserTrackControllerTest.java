package com.stackroute.usertrackservice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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

import com.stackroute.usertrackservice.domain.Artist;
import com.stackroute.usertrackservice.domain.Image;
import com.stackroute.usertrackservice.domain.Track;
import com.stackroute.usertrackservice.domain.User;
import com.stackroute.usertrackservice.exception.TrackAlreadyExistsException;
import com.stackroute.usertrackservice.service.UserTrackService;

@WebMvcTest(UserTrackController.class)
@RunWith(SpringRunner.class)
public class UserTrackControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserTrackService userTrackService;
	
	
	@InjectMocks
	private UserTrackController userTrackController;
	
	private User user;

	private Track track;
	
	private Artist artist;
	
	private Image img;
	private List<Track> trackList;

	
	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userTrackController).build();
		Image img = new Image(10, "www.google.com", "huge");
		Artist art = new Artist(20, "Bhupesh", "www.yahoo.com", img);
		track = new Track("10", "Test2", "Bhupesh Test", "123", "ibm.com", art);
		 trackList = new ArrayList();
		trackList.add(track);
		user = new User("Bhuoesh", "bhbothra@in.ibm.com", trackList);

		
	}
	
	@Test
	public void testTrackServiceSave() throws Exception {
		when(userTrackService.saveUserTrackToWishList(any(), eq(user.getUsername()))).thenReturn(user);
		mockMvc.perform(
				post("/api/v1/usertrackservice/user/{username}/track",user.getUsername())
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(track)))
				.andExpect(status().isCreated()).andDo(print());

		verify(userTrackService, times(1)).saveUserTrackToWishList(any(), eq(user.getUsername()));

	}
	
	@Test
	public void testSaveTrackFailure() throws Exception {
		when(userTrackService.saveUserTrackToWishList(any(), eq(user.getUsername()))).thenThrow(TrackAlreadyExistsException.class);	
		mockMvc.perform(
				post("/api/v1/usertrackservice/user/{username}/track",user.getUsername())
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(track)))
				.andExpect(status().isConflict()).andDo(print());

		verify(userTrackService, times(1)).saveUserTrackToWishList(any(), eq(user.getUsername()));

	}
	

	/*
	 * @Test public void testUpdateComment() throws Exception {
	 * when(userTrackService.updateCommentForTrack(track.getTrackId(),
	 * track.getComment(), user.getUsername())).thenReturn(user); mockMvc.perform(
	 * patch("/api/v1/usertrackservice/user/{username}/track",user.getUsername())
	 * .contentType(MediaType.APPLICATION_JSON) .content(jsonToString(track)))
	 * .andExpect(status().isOk()).andDo(print());
	 * 
	 * verify(userTrackService, times(1)).updateCommentForTrack(track.getTrackId(),
	 * track.getComment(), user.getUsername());
	 * 
	 * }
	 */
	
	@Test
	public void testDeleteTrack() throws Exception {
		when(userTrackService.deleteUserTrackToWishList(track.getTrackId(), user.getUsername())).thenReturn(user);
		mockMvc.perform(
				delete("/api/v1/usertrackservice/user/{username}/track",user.getUsername())
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(track)))
				.andExpect(status().isOk()).andDo(print());

		verify(userTrackService, times(1)).deleteUserTrackToWishList(track.getTrackId(), user.getUsername());

	}
	
	@Test
	public void testGetAllTrackFromWishList() throws Exception {
		when(userTrackService.getAllUserTrackFromWishList(user.getUsername())).thenReturn(trackList);
		mockMvc.perform(
				get("/api/v1/usertrackservice/user/{username}/track",user.getUsername())
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(track)))
				.andExpect(status().isOk()).andDo(print());

		verify(userTrackService, times(1)).getAllUserTrackFromWishList(user.getUsername());

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
