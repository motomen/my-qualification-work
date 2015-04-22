package com.goodfood.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

	@Id
	@Column(name = "id_user")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idUser;

	@Size(min = 5, max = 30)
	@NotNull
	private String login;

	@Size(min = 5, max = 16)
	@NotNull
	private String password;

	@NotEmpty @Email
	private String mail;

	@Column(name = "date_reg", nullable = false)
	private Date dateReg;
	private Date birthday;

	private String photo;

	@NotNull @Size(min = 4, max = 20)
	private String nicname;
	private String name;
	private String surname;

	@Column(name = "provider_name")
	private String providerName;

	@Column(name = "id_user_Str")
	private String idUserStr;

	@Column(name = "sex")
	private String Sex;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinTable(name="user_role",
			joinColumns = {@JoinColumn(name = "id_fk_user", referencedColumnName = "id_user")},
			inverseJoinColumns = {@JoinColumn(name = "id_fk_role", referencedColumnName = "id_role")})
	@LazyCollection(LazyCollectionOption.FALSE)
	private Role role;

	@OneToMany(mappedBy = "user")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Comments> commentsList;

	public String getSex() {
		return Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Date getDateReg() {
		return dateReg;
	}

	public void setDateReg(Date dateReg) {
		this.dateReg = dateReg;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getNicname() {
		return nicname;
	}

	public void setNicname(String nicname) {
		this.nicname = nicname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getIdUserStr() {
		return idUserStr;
	}

	public void setIdUserStr(String idUserStr) {
		this.idUserStr = idUserStr;
	}

	public List<Comments> getCommentsList() {
		return commentsList;
	}

	public void setCommentsList(List<Comments> commentsList) {
		this.commentsList = commentsList;
	}
}
