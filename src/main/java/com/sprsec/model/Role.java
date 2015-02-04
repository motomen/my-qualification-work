package com.sprsec.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Role {

	@Id
	@Column(name = "id_role")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idRole;
	@Column(name = "role_name")
	private String roleName;

	@OneToMany
	@JoinTable(name = "user_role",
			joinColumns = {@JoinColumn(name="id_fk_role", referencedColumnName = "id_role")},
			inverseJoinColumns = {@JoinColumn(name="id_fk_user", referencedColumnName = "id_user")})
	private List<User> user = new ArrayList<User>();

	public int getIdRole() {
		return idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}
	
}
