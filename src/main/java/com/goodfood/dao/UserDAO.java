package com.goodfood.dao;

import com.goodfood.model.User;

public interface UserDAO {
	
	public User getUser(String login);

	public User getUser(int id);

	public User getUserByEmail(String email);

	public void addUser(User user);

	public Boolean isLoginIdUnique(String login);
}
