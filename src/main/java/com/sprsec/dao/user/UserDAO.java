package com.sprsec.dao.user;

import com.sprsec.model.User;

public interface UserDAO {
	
	public User getUser(String login);

	public void addUser(User user);
}
