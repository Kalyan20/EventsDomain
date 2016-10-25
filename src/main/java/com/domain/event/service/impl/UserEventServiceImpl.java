package com.domain.event.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.domain.event.domain.UserEvent;
import com.domain.event.repositories.UserEventRepository;
import com.domain.event.service.UserEventService;

// This services creates the mapping of event with the user in a intermediate table 
@Service("userEventService")
public class UserEventServiceImpl implements UserEventService {
	
	@Resource
	UserEventRepository userEventRepo;

	@Override
	@Transactional(rollbackOn=Exception.class)
	public long createUserEvent(UserEvent userEvent) {
		UserEvent usrEvent = userEventRepo.save(userEvent);
		return usrEvent.getUserEventId();
	}

}
