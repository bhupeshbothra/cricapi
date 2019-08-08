package com.stackroute.usertrackservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stackroute.usertrackservice.domain.User;

public interface UserTrackRepository extends MongoRepository<User, String> {

	public User findByUsername(String username);
}
