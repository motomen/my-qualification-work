package com.sprsec.service.user;

import org.springframework.transaction.annotation.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprsec.dao.user.UserDAO;
import com.sprsec.model.User;

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

}
