package com.stackroute.authenticationservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.authenticationservice.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUsernameAndPassword(String userName, String password);
}
