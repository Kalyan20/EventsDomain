package com.domain.event.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.domain.event.domain.Event;

@Repository
public interface EventRepository  extends JpaRepository<Event, Long> {
	
	@Query("select e from Event e where e.eventName = ?1")
	public Event findByEventName(String eventName);

}
