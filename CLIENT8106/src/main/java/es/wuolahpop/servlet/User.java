package es.wuolahpop.servlet;

import java.io.Serializable;
import javax.persistence.*;


public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String mail;

	private String city;

	private String name;

	private String password;

	private String surname1;

	private String surname2;
	
	public User(String mail, String city, String name, String password, String surname1, String surname2) {
		super();
		this.mail = mail;
		this.city = city;
		this.name = name;
		this.password = password;
		this.surname1 = surname1;
		this.surname2 = surname2;
	}


	public User() {
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSurname1() {
		return this.surname1;
	}

	public void setSurname1(String surname1) {
		this.surname1 = surname1;
	}

	public String getSurname2() {
		return this.surname2;
	}

	public void setSurname2(String surname2) {
		this.surname2 = surname2;
	}


}