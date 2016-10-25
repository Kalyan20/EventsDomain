package com.domain.event.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.domain.event.domain.User;
import com.domain.event.dto.UserRequest;
import com.domain.event.repositories.UserRepository;
import com.domain.event.service.UserService;

//This service handles User related operations Create,update,get and delete
@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource 
	UserRepository userRepo;

	@Override
	@Transactional(rollbackOn=Exception.class)
	public long createUser(UserRequest userReq) throws Exception {
		// TODO Auto-generated method stub
		
		User savedUser = null;
		if(!userExists(userReq.getUserName()))
		{
			User user = new User();
			  user.setFrstName(userReq.getUserFirstName());
			  user.setLastName(userReq.getUserLastName());
			  user.setUserName(userReq.getUserName());
			 savedUser = userRepo.saveAndFlush(user);
		}
			
			
		return savedUser.getId();
	}

	@Override
	@Transactional(rollbackOn=Exception.class)
	public User updateUser(User user) throws Exception  {
		User updatedUser = userRepo.save(user);
		return updatedUser;
	}

	@Override
	@Transactional
	public void deleteUser(long id) throws Exception  {
		userRepo.delete(id);	
	}
	

	@Override
	@Transactional
	public User findById(long id) throws Exception  {
	
		User user = userRepo.findOne(id);
		return user;
	}


	
	private boolean userExists(String userName) {
	
		User user = userRepo.findByUserName(userName);
		if(userRepo.findByUserName(userName)!= null)
				return true;
		return false;
	}

	@Override
	public List<User> findAllUsers() throws Exception  {
		List<User> users = userRepo.findAll();
		return users;
	}

}
