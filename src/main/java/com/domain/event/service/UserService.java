package com.domain.event.service;

import java.util.List;

import com.domain.event.domain.User;
import com.domain.event.dto.UserRequest;

public interface UserService {
	
	public long createUser(UserRequest userReq)throws Exception;
	public User updateUser(User userReq)throws Exception;
	public void deleteUser(long id)throws Exception;
	public User findById(long id)throws Exception ;
	public List<User> findAllUsers()throws Exception ;
	

}
