package com.iteason.service;

import com.iteason.domain.User;

public interface UserService {
	
	public void saveUser(User u);

	public User getUserByPassword(User user);
}	
