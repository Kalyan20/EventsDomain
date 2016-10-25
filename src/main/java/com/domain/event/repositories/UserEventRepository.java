package com.domain.event.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.domain.event.domain.UserEvent;

@Repository
public interface UserEventRepository  extends JpaRepository<UserEvent, Long>{

}
