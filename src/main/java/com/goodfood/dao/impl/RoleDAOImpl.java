package com.goodfood.dao.impl;

import com.goodfood.dao.RoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.goodfood.model.Role;

@Repository
public class RoleDAOImpl implements RoleDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public Role getRole(int id) {
		Role role = (Role) getCurrentSession().load(Role.class, id);
		return role;
	}

}
