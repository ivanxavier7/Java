package com.ti.soap.documentsuploader.soap.service;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ti.soap.documentsuploader.bean.User;

@Component
public class UserDetailsService {
	
	public enum UserStatus {
		SUCCESS, FAILURE;
	}
	
	private static List<User> users = new ArrayList<>();
	
	static {
		User user1 = new User(1, "Ivan Xavier", "colaborador", "ivanxavier@ua.pt");
		users.add(user1);
		User user2 = new User(2, "Jeffery T. Dunn", "colaborador", "jefferydunn@ua.pt");
		users.add(user2);
		User user3 = new User(3, "Ernest J. Kostka", "colaborador", "ernestkostka@ua.pt");
		users.add(user3);
		User user4 = new User(4, "ESTGA", "empresarial", "estga.geral@ua.pt");
		users.add(user4);
	}
	
	// Find User by ID
	public User findById(int id) {
		for(User user:users) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	// Find users by Name
	public List<User> findByName(String name) {
		List<User> temp_documents = new ArrayList<>();
		for(User user:users) {
			if(user.getName().toLowerCase().contains(name.toLowerCase())) {
				temp_documents.add(user);
			}
		}
		return temp_documents;
	}
	
	// Find users by Type
	public List<User> findByType(String type) {
		List<User> temp_documents = new ArrayList<>();
		for(User user:users) {
			if(user.getType().toLowerCase().contains(type.toLowerCase())) {
				temp_documents.add(user);
			}
		}
		return temp_documents;
	}
	
	// Find users by Email
	public List<User> findByEmail(String email) {
		List<User> temp_documents = new ArrayList<>();
		for(User user:users) {
			if(user.getEmail().toLowerCase().contains(email.toLowerCase())) {
				temp_documents.add(user);
			}
		}
		return temp_documents;
	}
	
	// Find all Users
	public List<User> findAll() {
		return users;
	}
	
	// Delete User by ID
	public UserStatus deleteById(int id) {
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if(user.getId() == id) {
				iterator.remove();
				return UserStatus.SUCCESS;
			}
		}
		return UserStatus.FAILURE;
	}
	
	// Create User 
	public UserStatus create(int id, String name, String type, String email) {
		User user = new User(id, name, type, email);
		boolean userExists = false;
		
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			User userInDB = iterator.next();
			if(userInDB.getId() == id) {
				userExists = true;
			}
		}
		
		if (!userExists) {
			users.add(user);
			return UserStatus.SUCCESS;
		}
		else {
			return UserStatus.FAILURE;
		}
	}
	
	// Update User by ID
	public UserStatus update(int id, String name, String type, String email) {
		Iterator<User> iterator = users.iterator();
		User newUser = new User(id, name, type, email);
		
		while (iterator.hasNext()) {
			User oldUser = iterator.next();
			if(oldUser.getId() == id) {
				iterator.remove();
				users.add(newUser);
				return UserStatus.SUCCESS;
			}
		}
		return UserStatus.FAILURE;
	}
}
