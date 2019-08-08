package com.stackroute.usertrackservice.service;

import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.stackroute.usertrackservice.domain.Artist;
import com.stackroute.usertrackservice.domain.Image;
import com.stackroute.usertrackservice.domain.Track;
import com.stackroute.usertrackservice.domain.User;
import com.stackroute.usertrackservice.exception.TrackAlreadyExistsException;
import com.stackroute.usertrackservice.exception.TrackNotFoundException;
import com.stackroute.usertrackservice.repository.UserTrackRepository;

import junit.framework.Assert;

public class UserTrackServiceTest {

	@Mock
	UserTrackRepository userTrackRepository;
	
	private User user;

	private Track track;
	
	private Artist artist;
	
	private Image img;
	private List<Track> trackList;
	
	@InjectMocks
	public UserTrackServiceImpl userTrackServiceImpl;
	
	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);
		
		Image img = new Image(1, "www.google.com", "huge");
		Artist art = new Artist(2, "Bhupesh", "www.yahoo.com", img);
		track = new Track("1", "Test2", "Bhupesh Test", "123", "ibm.com", art);
		 trackList = new ArrayList();
		trackList.add(track);
		user = new User("Bhuoesh", "bhbothra@in.ibm.com", trackList);

		
	}
	
	@After
	public void delete() {
		userTrackRepository.deleteAll();
		user =  null;img = null ; track =null ; trackList =null;
	
	}
	
	@Test
	public void testsaveUserTrackList() throws TrackAlreadyExistsException {
		user = new User("Bhuoesh12", "bhbothra@in.ibm.com", null);

		Mockito.when(userTrackRepository.findByUsername(user.getUsername())).thenReturn(user);
		User fetchedUser = userTrackServiceImpl.saveUserTrackToWishList(track, user.getUsername());
		Assert.assertEquals(fetchedUser, user);
		verify(userTrackRepository, timeout(1)).findByUsername(user.getUsername());
		verify(userTrackRepository, times(1)).save(user);

	}
	
	@Test
	public void testdeleteUserTrackFromWishList() throws  TrackNotFoundException {
		//user = new User("Bhuoesh12", "bhbothra@in.ibm.com", null);

		Mockito.when(userTrackRepository.findByUsername(user.getUsername())).thenReturn(user);
		User fetchedUser = userTrackServiceImpl.deleteUserTrackToWishList(track.getTrackId(), user.getUsername());
		Assert.assertEquals(fetchedUser, user);
		verify(userTrackRepository, timeout(1)).findByUsername(user.getUsername());
		verify(userTrackRepository, times(1)).save(user);

	}
	
	@Test
	public void testupdateCommentforTrack() throws  TrackNotFoundException {
		//user = new User("Bhuoesh12", "bhbothra@in.ibm.com", null);

		Mockito.when(userTrackRepository.findByUsername(user.getUsername())).thenReturn(user);
		User fetchedUser = userTrackServiceImpl.updateCommentForTrack(track.getTrackId(), "new comment", user.getUsername());
		Assert.assertEquals(fetchedUser.getTrackList().get(0).getComment(), user.getTrackList().get(0).getComment());
		verify(userTrackRepository, timeout(1)).findByUsername(user.getUsername());
		verify(userTrackRepository, times(1)).save(user);

	}
	
	@Test
	public void testgetAllTracksForUser() throws  Exception {
		//user = new User("Bhuoesh12", "bhbothra@in.ibm.com", null);

		Mockito.when(userTrackRepository.findByUsername(user.getUsername())).thenReturn(user);
		List fetchedList = userTrackServiceImpl.getAllUserTrackFromWishList(user.getUsername());
		Assert.assertEquals(fetchedList, trackList);
		verify(userTrackRepository, times(1)).findByUsername(user.getUsername());
		//verify(userTrackRepository, times(1)).save(user);

	}
	
	
	
}
