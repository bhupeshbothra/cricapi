package com.bhupesh.cricapi.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CricPlayInformation {

	private String name;
	private String email;
	private int score;
	private int cricplayerid;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int Id;

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

	/**
	 * @return the id
	 */
	public int getId() {
		return Id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		Id = id;
	}
	
}
