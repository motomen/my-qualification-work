package com.goodfood.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.goodfood.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.goodfood.model.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session openSession() {
		return sessionFactory.getCurrentSession();
	}

	public User getUser(String login) {
		List<User> userList = new ArrayList<User>();
		Query query = openSession().createQuery("from User u where u.login = :login");
		query.setParameter("login", login);
		userList = query.list();
		if (userList.size() > 0)
			return userList.get(0);
		else
			return null;	
	}

	@Override
	public void addUser(User user) {
		openSession().save(user);
	}

	@Override
	public Boolean isLoginIdUnique(String login) {
		if (getUser(login) == null) {
			return true;
		} else {
			return false;
		}
	}

}