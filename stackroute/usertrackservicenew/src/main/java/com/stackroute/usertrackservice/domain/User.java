package com.stackroute.usertrackservice.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {

	@Id
	private String username;
	private String email;
	private List<Track> trackList;
	
	
	public User() {
		super();
	}
	public User(String userName, String email, List<Track> trackList) {
		super();
		this.username = userName;
		this.email = email;
		this.trackList = trackList;
	}
	/**
	 * @return the userName
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.username = userName;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the trackList
	 */
	public List<Track> getTrackList() {
		return trackList;
	}
	/**
	 * @param trackList the trackList to set
	 */
	public void setTrackList(List<Track> trackList) {
		this.trackList = trackList;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [userName=" + username + ", email=" + email + ", trackList=" + trackList + "]";
	}
	
}
