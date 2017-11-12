package com.domain.event.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.domain.event.domain.Event;
import com.domain.event.dto.EventRequest;
import com.domain.event.error.handler.ErrorHandler;
import com.domain.event.service.EventService;


@RestController
@RequestMapping(value = "/events")
public class EventController {

	private static final Logger L = Logger.getLogger(EventController.class);

	@Autowired
	EventService eventService;

	/*
	 * This method returns list of Events in pageable fashion . "pageNumber" is the
	 * page number coming in
	 * 
	 * @returns Page<Event> Status codes: 200, 500 , 404
	 */

	@RequestMapping(value = "/{pageNumber}", method = RequestMethod.GET)
	public ResponseEntity<Page<Event>> listAllEvents(@PathVariable Integer pageNumber) {
		Page<Event> events = null;
		try {
			events = eventService.findAllEvents(pageNumber);
			if (events == null)
				return new ResponseEntity<Page<Event>>(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Page<Event>>(events, HttpStatus.OK);
		} catch (Exception e) {
			L.error(e.getMessage());
			return ErrorHandler.toError(e);
		}

	}

	/*
	 * This method creates a event
	 * 
	 * @returns Long EventId Status codes: 201, 500 , 409
	 */

	@RequestMapping(value = "/event", method = RequestMethod.POST)
	public ResponseEntity<Long> createEvent(@RequestBody EventRequest req) {
		Long eventId = null;
		try {
			eventId = eventService.createEvent(req);
			return new ResponseEntity<Long>(eventId, HttpStatus.CREATED);
		} catch (Exception e) {
			L.error(e.getMessage());
			return ErrorHandler.toError(e);
		}

	}

	/*
	 * This method updates a event
	 * 
	 * @returns Event with updated information Status codes: 200, 500 , 404
	 */

	@RequestMapping(value = "/event/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Event> updateEvent(@PathVariable("id") long id, @RequestBody EventRequest event) {
		L.info("Updating User " + id);
		Event currentEvent = null;
		try {
			currentEvent = eventService.findById(id);
			L.info("findById " + "id" + currentEvent);
			if (currentEvent == null)
				return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
			currentEvent.setDesc(event.getDesc());
			currentEvent.setEventName(event.getEventName());
			eventService.updateEvent(currentEvent);
			return new ResponseEntity<Event>(currentEvent, HttpStatus.OK);
		} catch (Exception e) {
			L.error(e.getMessage());
			return ErrorHandler.toError(e);
		}

	}

	/*
	 * This method gets an event
	 * 
	 * @returns Event with details Status codes: 200, 500 , 404
	 */

	@RequestMapping(value = "/event/{id}", method = RequestMethod.GET)
	public ResponseEntity<Event> getEvent(@PathVariable("id") long id) {
		L.info("Fetching Event " + id);
		Event currentEvent = null;
		try {
			currentEvent = eventService.findById(id);

			if (currentEvent == null)
				return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Event>(currentEvent, HttpStatus.OK);

		} catch (Exception e) {
			return ErrorHandler.toError(e);
		}

	}

	/*
	 * This method deletes an event
	 * 
	 * @returns Status codes: 204, 500 , 404
	 */

	@RequestMapping(value = "/event/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Event> deleteEvent(@PathVariable("id") long id) {
		L.info("Fetching & Deleting Event with id " + id);
		try {
			Event event = eventService.findById(id);
			if (event == null)
				return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
			eventService.deleteEvent(id);
			return new ResponseEntity<Event>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return ErrorHandler.toError(e);

		}

	}

	
}
