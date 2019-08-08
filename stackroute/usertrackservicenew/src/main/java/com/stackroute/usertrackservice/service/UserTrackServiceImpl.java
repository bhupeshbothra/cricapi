package com.stackroute.usertrackservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.usertrackservice.domain.Track;
import com.stackroute.usertrackservice.domain.User;
import com.stackroute.usertrackservice.exception.TrackAlreadyExistsException;
import com.stackroute.usertrackservice.exception.TrackNotFoundException;
import com.stackroute.usertrackservice.exception.UserAlreadyExistsException;
import com.stackroute.usertrackservice.repository.UserTrackRepository;

@Service
public class UserTrackServiceImpl implements UserTrackService {

	public UserTrackRepository userTrackRepository;

	@Autowired
	public UserTrackServiceImpl(UserTrackRepository userTrackRepository) {
		super();
		this.userTrackRepository = userTrackRepository;
	}

	@Override
	public User saveUserTrackToWishList(Track track, String username) throws TrackAlreadyExistsException {
		// TODO Auto-generated method stub

		User fetchedUserObj = userTrackRepository.findByUsername(username);

		List<Track> trackList = fetchedUserObj.getTrackList();

		if (trackList != null) {
			for (Track t : trackList) {

				if (t.getTrackId().equals(track.getTrackId())) {
					throw new TrackAlreadyExistsException();
				}
			}
			trackList.add(track);
			fetchedUserObj.setTrackList(trackList);
			userTrackRepository.save(fetchedUserObj);

		}

		else {
			trackList = new ArrayList();
			trackList.add(track);

			fetchedUserObj.setTrackList(trackList);
			userTrackRepository.save(fetchedUserObj);
		}
		return fetchedUserObj;
	}

	@Override
	public User deleteUserTrackToWishList(String trackId, String username) throws TrackNotFoundException {
		// TODO Auto-generated method stub
		User fetchedUserObj = userTrackRepository.findByUsername(username);
		boolean trackFound = false;
		int indexnum =0;
		List<Track> trackList = fetchedUserObj.getTrackList();

		if (trackList!=null && trackList.size()> 0) {
			for (Track t : trackList) {
				indexnum++;
				if (t.getTrackId().equals(trackId)) {
					trackList.remove(indexnum-1);
					fetchedUserObj.setTrackList(trackList);
					userTrackRepository.save(fetchedUserObj);
					break;
				}
			}
			

		}

		else {
			throw new TrackNotFoundException();
		}
		return fetchedUserObj;
	}

	@Override
	public List<Track> getAllUserTrackFromWishList(String username) throws Exception {
		// TODO Auto-generated method stub
		User fetchedUserObj = userTrackRepository.findByUsername(username);
	
		return fetchedUserObj.getTrackList();
	}

	@Override
	public User updateCommentForTrack(String trackId, String comments, String username) throws TrackNotFoundException {
		// TODO Auto-generated method stub
		User fetchedUserObj = userTrackRepository.findByUsername(username);
		boolean trackFound = false;
		int indexnum =0;
		List<Track> trackList = fetchedUserObj.getTrackList();

		if (trackList!=null && trackList.size()> 0) {
			for (Track t : trackList) {
				indexnum++;
				if (t.getTrackId().equals(trackId)) {
					t.setComment(comments);
					fetchedUserObj.setTrackList(trackList);
					userTrackRepository.save(fetchedUserObj);
					break;
				}
			}
			

		}

		else {
			throw new TrackNotFoundException();
		}
		return fetchedUserObj;
	}

	@Override
	public User registerUser(User user) throws UserAlreadyExistsException {
		// TODO Auto-generated method stub
		
		System.out.println(user.toString());
		User fetchedUserObj = userTrackRepository.findByUsername(user.getUsername());

		if (fetchedUserObj != null) {
			throw new UserAlreadyExistsException();
		} else {
			userTrackRepository.save(user);
		}
		return user;
	}

}
