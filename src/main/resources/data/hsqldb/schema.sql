DROP TABLE EVENTS IF exists;

CREATE TABLE EVENTS
(
EVENT_NAME varchar(255),
DESCRIPTION varchar(255),
EVENT_ID int,
PRIMARY KEY (EVENT_ID) 
);

DROP TABLE USERS IF exists;

CREATE TABLE USERS
(
USER_ID int,
USER_NAME varchar(255),
USER_FIRST_NAME varchar(255),
USER_LAST_NAME varchar(255),
PRIMARY KEY (USER_ID) 


);

DROP  TABLE USER_EVENTS IF exists ;

CREATE TABLE USER_EVENTS
(
user_event_id int,
user_id int,
event_id int,
PRIMARY KEY (user_event_id) 
);

