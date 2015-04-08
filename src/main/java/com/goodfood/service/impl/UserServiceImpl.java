package com.goodfood.service.impl;

import com.goodfood.service.UserService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.goodfood.dao.UserDAO;
import com.goodfood.model.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	public User getUser(String login) {
		return userDAO.getUser(login);
	}

	@Override
	public void addUser(User user) {
		userDAO.addUser(user);
	}

	@Override
	public Boolean isLoginIdUnique(String login) {
		return userDAO.isLoginIdUnique(login);
	}

}
