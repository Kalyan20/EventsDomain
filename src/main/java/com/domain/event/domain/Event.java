package com.domain.event.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="EVENTS")
public class Event {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="EVENT_ID")
	private Long id;
	
	@Column(name="EVENT_NAME")
	private String eventName;
	
	@Column(name="DESCRIPTION")
	private String desc;
	
	@OneToMany
	@JoinColumn(name = "EVENT_ID")
	private List<UserEvent> userEvent = new ArrayList<UserEvent>();

	
	public List<UserEvent> getUserEvent() {
		return userEvent;
	}

	public void setUserEvent(List<UserEvent> userEvent) {
		this.userEvent = userEvent;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	

}
