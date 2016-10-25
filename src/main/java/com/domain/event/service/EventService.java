package com.domain.event.service;

import java.util.List;

import com.domain.event.domain.Event;
import com.domain.event.dto.EventRequest;
import org.springframework.data.domain.Page;

public interface EventService {
	
	public Page<Event> findAllEvents(Integer pageNumber)throws Exception;
	public long createEvent(EventRequest req)throws Exception;
	public Event updateEvent(Event eventReq)throws Exception;
	public Event findById(long id)throws Exception;
	public void deleteEvent(long id)throws Exception;
	public Event findByEventName(String eventName)throws Exception;
}
