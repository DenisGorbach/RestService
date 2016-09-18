package com.doit.dao;

import com.doit.data.User;

import java.util.List;

public interface UserDao {
 
	User get(int id);
 	List<User> getAll();
	boolean put(int id, User user);
	boolean post(User user);
	boolean delete(int id);
	List<User> getByRole(int role);
}