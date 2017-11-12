package com.domain.event.error.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.domain.event.domain.Event;
import com.google.common.base.Throwables;
import org.apache.log4j.Logger;

public class ErrorHandler {
	private static final Logger L = Logger.getLogger(ErrorHandler.class);

	public static ResponseEntity toError(Exception e) {
		final Throwable rootCause = Throwables.getRootCause(e);
		final boolean isBadRequest = rootCause instanceof IllegalArgumentException;
		L.error("unexpected error, errorId: %s", rootCause);

		if (isBadRequest) {
			return new ResponseEntity<Event>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Event>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
