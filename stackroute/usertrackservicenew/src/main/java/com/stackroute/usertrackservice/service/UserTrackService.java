package com.stackroute.usertrackservice.service;

import java.util.List;

import com.stackroute.usertrackservice.domain.Track;
import com.stackroute.usertrackservice.domain.User;
import com.stackroute.usertrackservice.exception.TrackAlreadyExistsException;
import com.stackroute.usertrackservice.exception.TrackNotFoundException;
import com.stackroute.usertrackservice.exception.UserAlreadyExistsException;

public interface UserTrackService {

	public User saveUserTrackToWishList(Track track, String username) throws TrackAlreadyExistsException;
	public User deleteUserTrackToWishList(String trackId , String username ) throws TrackNotFoundException;

	public List<Track> getAllUserTrackFromWishList(String username ) throws Exception;
	public User updateCommentForTrack(String trackId , String comments, String username ) throws TrackNotFoundException;
	public User registerUser(User user) throws UserAlreadyExistsException;
}
