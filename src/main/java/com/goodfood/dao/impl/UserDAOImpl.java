package com.goodfood.dao.impl;


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
		Query query = openSession().createQuery("from User u where u.login = :login");
		query.setParameter("login", login);
		return (User) query.uniqueResult();
	}

	@Override
	public User getUser(int id) {
		Query query = openSession().createQuery("from User u where u.idUser = :id");
		query.setParameter("id", id);
		return (User) query.uniqueResult();
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
