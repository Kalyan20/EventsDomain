package com.domain.event.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ID")
	private Long id;
	
	@Column(name="USER_NAME")
	private String userName;
	
	@Column(name="USER_FIRST_NAME")
	private String frstName;

	
	@Column(name="USER_LAST_NAME")
	private String lastName;
	
	 @OneToMany
	    @JoinTable(
	        name="USER_EVENTS",
	        joinColumns = { @JoinColumn( name="USER_ID",referencedColumnName="USER_ID") },
	        inverseJoinColumns = { @JoinColumn( name="EVENT_ID" , referencedColumnName="EVENT_ID" , unique =true)}
	    )
		
		private List<Event> events;
	
	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFrstName() {
		return frstName;
	}

	public void setFrstName(String frstName) {
		this.frstName = frstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	
}
