package com.domain.event.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.domain.event.domain.Event;
import com.domain.event.dto.EventRequest;
import com.domain.event.repositories.EventRepository;
import com.domain.event.service.EventService;

// This service handles Event related operations Create,update,get and delete

@Service("EventService")
public class EventServiceImpl implements EventService {
	
	@Resource
	EventRepository eventRepo;

	 private static final int PAGE_SIZE = 3;
	@Override
	@Transactional
	public Page<Event> findAllEvents(Integer pageNumber) {
		
		PageRequest request =
	            new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "eventName");
		 
		Page<Event> events = eventRepo.findAll(request);
		return events;
	}

	@Override
	@Transactional(rollbackOn=Exception.class)
	public long createEvent(EventRequest req) {
		Event event = null;
		if(!eventExists(req.getEventName()))
		{
		event = new Event();
		event.setEventName(req.getEventName());
		event.setDesc(req.getDesc());
		eventRepo.saveAndFlush(event);
		}
		return event.getId();
	}

	@Override
	public Event findById(long id) {
		Event event = eventRepo.findOne(id);
		return event;
	}
	

	@Override
	@Transactional(rollbackOn=Exception.class)
	public Event updateEvent(Event event) throws Exception {
		
		Event updatedEvent = eventRepo.save(event);
		return updatedEvent;
	}

	
	private boolean eventExists(String eventName) {
		
		Event event = eventRepo.findByEventName(eventName);
		if(event!= null)
				return true;
		return false;
	}

	@Override
	@Transactional(rollbackOn=Exception.class)
	public void deleteEvent(long id) {
		eventRepo.delete(id);
		
	}

	@Override
	public Event findByEventName(String eventName) throws Exception {
		Event event = eventRepo.findByEventName(eventName);
		
		return event;
	}

}
