DROP TABLE EVENTS IF exists;

CREATE TABLE EVENTS
(
EVENT_NAME varchar(255),
DESCRIPTION varchar(255),
EVENT_ID int
);

DROP TABLE USERS IF exists;

CREATE TABLE USERS
(
USER_ID int,
USER_NAME varchar(255),
USER_FIRST_NAME varchar(255),
USER_LAST_NAME varchar(255)


);

DROP  TABLE USER_EVENTS IF exists ;

CREATE TABLE USER_EVENTS
(
user_event_id int,
user_id int,
event_id int
);

