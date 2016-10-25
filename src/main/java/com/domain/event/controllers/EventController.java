package com.domain.event.controllers;

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
import com.domain.event.service.EventService;

@RestController
@RequestMapping(value = "/events")
public class EventController {
	
	@Autowired
	EventService  eventService;
	
	
	 /* This method returns list of Events in pageable fashion . "pageNumber" is the page number coming in
	  * @returns Page<Event>
	  * Status codes: 200, 500 , 404
	  */
    
	@RequestMapping(value = "/{pageNumber}", method = RequestMethod.GET)
	  public ResponseEntity<Page<Event>> listAllEvents(@PathVariable Integer pageNumber) {
		Page<Event> events = null;
			try {
				events = eventService.findAllEvents(pageNumber);
			} catch (Exception e) {
				 return new ResponseEntity<Page<Event>>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	        if(events==null){
	            return new ResponseEntity<Page<Event>>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<Page<Event>>(events, HttpStatus.OK);
	    }
	
	
	 
	 /* This method creates a event
	  * @returns Long EventId
	  * Status codes: 201, 500 , 409
	  */
	    	 
	 @RequestMapping(value = "/event", method = RequestMethod.POST)
	  public ResponseEntity<Long> createEvent(@RequestBody EventRequest req) {
	      Long eventId=null;
		try {
			eventId = eventService.createEvent(req);
		} catch (Exception e) {
			return new ResponseEntity<Long>(HttpStatus.CONFLICT);
		}
	       
	        return new ResponseEntity<Long>(eventId, HttpStatus.CREATED);
	    }
	 
	 
	 /* This method updates a event
	  * @returns Event with updated information
	  * Status codes: 200, 500 , 404
	  */
     
	    @RequestMapping(value = "/event/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<Event> updateEvent(@PathVariable("id") long id, @RequestBody EventRequest event) {
	        System.out.println("Updating User " + id);
	        Event currentEvent = null;
	        try { 
	            currentEvent = eventService.findById(id);
	         
	        if (currentEvent==null) {
	            System.out.println("Event with id " + id + " not found");
	            return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
	        }
	        currentEvent.setDesc(event.getDesc());
	        currentEvent.setEventName(event.getEventName());
	      
	        eventService.updateEvent(currentEvent);
			} catch (Exception e) {
				 return new ResponseEntity<Event>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	        return new ResponseEntity<Event>(currentEvent, HttpStatus.OK);
	    }
	    
	    
	    /* This method gets an event
		  * @returns Event with details
		  * Status codes: 200, 500 , 404
		  */
	     
	    @RequestMapping(value = "/event/{id}", method = RequestMethod.GET)
	    public ResponseEntity<Event> getEvent(@PathVariable("id") long id) {
	        System.out.println("Updating Event " + id);
	        Event currentEvent = null;
	        try { 
	            currentEvent = eventService.findById(id);
	         
	        if (currentEvent==null) {
	            System.out.println("Event with id " + id + " not found");
	            return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
	        }

			} catch (Exception e) {
				 return new ResponseEntity<Event>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	        return new ResponseEntity<Event>(currentEvent, HttpStatus.OK);
	    }
	 
	    /* This method deletes an event
		  * @returns 
		  * Status codes: 204, 500 , 404
		  */
	     
	    @RequestMapping(value = "/event/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<Event> deleteEvent(@PathVariable("id") long id) {
	        System.out.println("Fetching & Deleting Event with id " + id);
	        try {
	        Event event = eventService.findById(id);
	        if (event == null) {
	            System.out.println("Unable to delete. event with id " + id + " not found");
	            return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
	        }
	        eventService.deleteEvent(id);
			} catch (Exception e) {
				 return new ResponseEntity<Event>(HttpStatus.INTERNAL_SERVER_ERROR);
			
			}
	        return new ResponseEntity<Event>(HttpStatus.NO_CONTENT);
	    }
	 
	

}
