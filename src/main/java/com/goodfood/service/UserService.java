package com.goodfood.service;

import com.goodfood.model.User;

public interface UserService {
	
	public User getUser(String login);

	public void addUser(User user);

	public User getUser(int id);

	public User getUserByEmail(String email);

	public Boolean isLoginIdUnique(String login);
}
