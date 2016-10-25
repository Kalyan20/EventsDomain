package com.domain.event.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.domain.event.domain.Event;
import com.domain.event.domain.User;
import com.domain.event.domain.UserEvent;
import com.domain.event.dto.UserRequest;
import com.domain.event.service.EventService;
import com.domain.event.service.UserEventService;
import com.domain.event.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	EventService eventService;
	
	@Autowired
	UserEventService userEventService;
	
	
	 /* This method returns list of users
	  * @returns List<User>
	  * Status codes: 200, 500 , 404
	  */
    	
	  @RequestMapping(method = RequestMethod.GET)
	  public ResponseEntity<List<User>> listAllUsers() {
	        List<User> users = null;
			try {
				users = userService.findAllUsers();
			} catch (Exception e) {
				 return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	        if(users.isEmpty()){
	            return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	    }


	  
	  /* This method Creates User
		  * @returns Long (UserId)
		  * Status codes: 201, 500 , 409
		  */
	    
	 @RequestMapping(value = "/user", method = RequestMethod.POST)
	  public ResponseEntity<Long> createUser(@RequestBody UserRequest user) {
	      Long userId;
		try {
			userId = userService.createUser(user);
		} catch (Exception e) {
			return new ResponseEntity<Long>(HttpStatus.CONFLICT);
		}
	       
	        return new ResponseEntity<Long>(userId, HttpStatus.CREATED);
	    }
	 

	 /* This method gets a  User
	  * @returns User object
	  * Status codes: 200, 500 , 404
	  */
     
	 @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
	        System.out.println("Fetching User with id " + id);
	        User user = null;
			try {
				user = userService.findById(id);
			} catch (Exception e) {
				 return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	        if (user == null) {
	            System.out.println("User with id " + id + " not found");
	            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<User>(user, HttpStatus.OK);
	    }
	 
	 
	 /* This method updates a  User
	  * @returns User object
	  * Status codes: 200, 500 , 404
	  */
     
	    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody UserRequest user) {
	        System.out.println("Updating User " + id);
	        User currentUser = null;
	        try { 
	            currentUser = userService.findById(id);
	         
	        if (currentUser==null) {
	            System.out.println("User with id " + id + " not found");
	            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	        }
	        currentUser.setFrstName(user.getUserFirstName());
	        currentUser.setLastName(user.getUserLastName());
	        currentUser.setUserName(user.getUserName());
			userService.updateUser(currentUser);
			} catch (Exception e) {
				 return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	    }
	    
	 
	    /* This method deletes a  User
		  * @returns no content after successful deletion
		  * Status codes: 204, 500 , 404
		  */
	    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
	        System.out.println("Fetching & Deleting User with id " + id);
	        try {
	        User user = userService.findById(id);
	        if (user == null) {
	            System.out.println("Unable to delete. User with id " + id + " not found");
	            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	        }
				userService.deleteUser(id);
			} catch (Exception e) {
				 return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	    }
	    
	    
	    

	    /* This method registers a User to a event
			  * @returns User with Event details
			  * Status codes: 200, 500 , 404
			  */
	     
	    @RequestMapping(value = "/user/{id}/event", method = RequestMethod.POST)
	    public ResponseEntity<User> registerEvent(@PathVariable("id") long id,  @RequestBody UserRequest userReq) {
	        System.out.println("Fetching User with id " + id);
	        User user = null;
	        try {
	        user = userService.findById(id);
	        if (user == null) {
	            System.out.println("Unable to find. User with id " + id + " not found");
	            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	        }
	           Event event = eventService.findByEventName(userReq.getEventName());
	           
	           if(event !=  null)
	           {
	           UserEvent userEvent = new UserEvent();
	           userEvent.setEventId(event.getId());
	           userEvent.setUserId(user.getId());
	           event.getUserEvent().add(userEvent);
	           userEventService.createUserEvent(userEvent);
	           }
	           
	           user = userService.findById(id);
			} catch (Exception e) {
				 return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	        
	        return new ResponseEntity<User>(user , HttpStatus.OK);
	    }
	    
	     
	    
	    
	    
	  
}
