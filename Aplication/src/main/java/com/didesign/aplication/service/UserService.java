package com.didesign.aplication.service;

import javax.validation.Valid;

import com.didesign.aplication.entity.User;

public interface UserService {

	public Iterable<User> getAllUsers();

	public User createUser(User user) throws Exception;
		
}