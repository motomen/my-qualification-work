package com.sprsec.service.user;

import com.sprsec.model.User;

public interface UserService {
	
	public User getUser(String login);

	public void addUser(User user);
}
