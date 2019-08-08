package com.bhupesh.cricapi.domain;

public class CricPlayer {

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	private String name;
	private String email;
	private int score;
	private int cricplayerid;
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CricPlayer [name=" + name + ", email=" + email + ", score=" + score + ", CricPlayerId=" + cricplayerid
				+ "]";
	}
	/**
	 * @return the cricplayerid
	 */
	public int getCricplayerid() {
		return cricplayerid;
	}
	/**
	 * @param cricplayerid the cricplayerid to set
	 */
	public void setCricplayerid(int cricplayerid) {
		this.cricplayerid = cricplayerid;
	}
	public CricPlayer() {
		super();
	}
	
	
}
