package com.stackroute.usertrackservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.usertrackservice.domain.Track;
import com.stackroute.usertrackservice.domain.User;
import com.stackroute.usertrackservice.exception.TrackAlreadyExistsException;
import com.stackroute.usertrackservice.exception.TrackNotFoundException;
import com.stackroute.usertrackservice.exception.UserAlreadyExistsException;
import com.stackroute.usertrackservice.service.UserTrackService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/usertrackservice")
public class UserTrackController {
	
	private UserTrackService userTrackService;
	private ResponseEntity responseEntity;
	
	public UserTrackController() {
		super();
	}

	@Autowired
	public UserTrackController(UserTrackService userTrackService) {
		super();
		this.userTrackService = userTrackService;
	}
	
	@PostMapping("/register")
	public ResponseEntity registerUser(@RequestBody User user) throws UserAlreadyExistsException {
		
		try {
			System.out.println("user info in User Track Controller" +user.toString());
			User fetchedUser = userTrackService.registerUser(user);
			responseEntity = new ResponseEntity<User>(user, HttpStatus.CREATED);
		} catch (UserAlreadyExistsException e) {
			// TODO Auto-generated catch block
			throw new UserAlreadyExistsException();
		}
		return responseEntity;
		
	}
	
	@PostMapping("/user/{username}/track")
	public ResponseEntity<?> addUserTrackToWishList(@RequestBody Track track, @PathVariable String username) throws TrackAlreadyExistsException {
		
		try {
			User fetchedUser = userTrackService.saveUserTrackToWishList(track, username);
			responseEntity = new ResponseEntity<User>(fetchedUser, HttpStatus.CREATED);
		} catch (TrackAlreadyExistsException e) {
			// TODO Auto-generated catch block
			throw new TrackAlreadyExistsException();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return responseEntity;
		
	}
	
	@DeleteMapping("/user/{username}/track")
	public ResponseEntity<?> deleteTrackToWishList(@RequestBody Track track, @PathVariable String username) throws TrackNotFoundException {
		
		try {
			User fetchedUser = userTrackService.deleteUserTrackToWishList(track.getTrackId(), username);
			responseEntity = new ResponseEntity<User>(fetchedUser, HttpStatus.OK);
		} catch (TrackNotFoundException e) {
			// TODO Auto-generated catch block
			throw new TrackNotFoundException();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return responseEntity;
		
	}
	
	@PutMapping("/user/{username}/track")
	public ResponseEntity<?> updateCommentForUserTrack(@RequestBody Track track, @PathVariable String username, String comments) throws TrackNotFoundException {
		
		try {
			User fetchedUser = userTrackService.updateCommentForTrack(track.getTrackId(), comments, username);
			responseEntity = new ResponseEntity<User>(fetchedUser, HttpStatus.OK);
		} catch (TrackNotFoundException e) {
			// TODO Auto-generated catch block
			throw new TrackNotFoundException();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return responseEntity;
		
	}
	
	@GetMapping("/user/{username}/track")
	public ResponseEntity<?> getAllUserTrackList(@PathVariable String username) throws TrackNotFoundException {
		
		try {
			List<Track> trackList = userTrackService.getAllUserTrackFromWishList(username);
			responseEntity = new ResponseEntity(trackList, HttpStatus.OK);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return responseEntity;
		
	}
	

}
