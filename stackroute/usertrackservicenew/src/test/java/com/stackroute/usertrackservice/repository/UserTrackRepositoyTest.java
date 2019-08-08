package com.stackroute.usertrackservice.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.usertrackservice.domain.Artist;
import com.stackroute.usertrackservice.domain.Image;
import com.stackroute.usertrackservice.domain.Track;
import com.stackroute.usertrackservice.domain.User;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UserTrackRepositoyTest {

	@Autowired
	private UserTrackRepository userTrackRepository;

	private User user;

	private Track track;

	@Before
	public void setup() {
		Image img = new Image(1, "www.google.com", "huge");
		Artist art = new Artist(2, "Bhupesh", "www.yahoo.com", img);
		track = new Track("1", "Test2", "Bhupesh Test", "123", "ibm.com", art);
		List<Track> trackList = new ArrayList();
		trackList.add(track);
		user = new User("Bhuoesh", "bhbothra@in.ibm.com", trackList);

	}

	@After
	public void delete() {
		userTrackRepository.deleteAll();
		user = null;
	}

	@Test
	public void testSaveUserTrack() {
		userTrackRepository.save(user);
		User userObj = userTrackRepository.findByUsername(user.getUsername());
		List<Track> trackList = userObj.getTrackList();
		Assert.assertEquals(trackList.get(0).getTrackId(), user.getTrackList().get(0).getTrackId());
		userTrackRepository.delete(user);
	}

}
