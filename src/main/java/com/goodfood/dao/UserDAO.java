package com.goodfood.dao;

import com.goodfood.model.User;

public interface UserDAO {
	
	public User getUser(String login);

	public void addUser(User user);

	public Boolean isLoginIdUnique(String login);

	public User getUserByMail(String mail);
}
