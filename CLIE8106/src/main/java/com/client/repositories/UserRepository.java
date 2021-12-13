package com.client.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.client.domains.User;

public interface UserRepository extends CrudRepository<User, String> {
	public User findUserByMail(String mail);
	public User findUserByName(String name);
	public List <User> findUsersByName(String name);
	public List <User> findUsersByCity(String city);
}
