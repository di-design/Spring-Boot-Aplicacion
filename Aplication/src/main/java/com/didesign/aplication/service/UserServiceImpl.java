package com.didesign.aplication.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.didesign.aplication.entity.User;
import com.didesign.aplication.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	private boolean checkUsernameAvailable(User user) throws Exception {
		Optional<User> userFound = userRepository.findByUsername(user.getUsername());
		
		if(userFound.isPresent()) {
			throw new Exception ("Username no disponible"); 
		}else {
			return true;
		}
	}
	
	private boolean checkPasswordValid(User user) throws Exception {
		
		if (user.getConfirmPassword() == null || user.getConfirmPassword().isEmpty()) {
			throw new Exception("Confirm Password es obligatorio");
		}
		
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			throw new Exception ("La contraseña no coincide");
		}else {
			return true;
		}
	}

	@Override
	public User createUser(User user) throws Exception {
		if(checkUsernameAvailable(user) && checkPasswordValid(user)) {
			user= userRepository.save(user);
		}
		return user;
	}

	@Override
	public User getUserById(Long id) throws Exception {
		return userRepository.findById(id).orElseThrow(() -> new Exception("El usuario no existe"));
	}

	@Override
	public User updateUser(User fromUser) throws Exception {
			User toUser = getUserById(fromUser.getId());
			mapUser(fromUser, toUser);
			return userRepository.save(toUser);

	}
	
	protected void mapUser (User from, User to) {
		to.setUsername(from.getUsername());
		to.setFirstName(from.getFirstName());
		to.setLastName(from.getLastName());
		to.setEmail(from.getEmail());
		to.setRoles(from.getRoles());
		//to.setPassword(from.getPassword());
	}
	
	public void deleteUser(Long id) throws Exception {
		User user = getUserById(id);

		userRepository.delete(user);
	}

}
