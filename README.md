# EventsDomain
This porject is built on Spring boot with Spring JPA data and inbuild inmemory (H2 hsqlDB)
Services are implemented with Spring Rest controller

Data creation is happened when the endpoints are invoked and autommatic deletion of data when the server is shut down.


Endpoints:

EVENTS :
http://localhost:8090/domain/events/event (post call Event creation)
http://localhost:8090/domain/events/event/{id}( GET call for a specific event)
http://localhost:8090/domain/events/event/{id}( PUT call for a specific event)
http://localhost:8090/domain/events/event/{id}( DELETE call for a specific event)
http://localhost:8090/domain/events/event/{id}( PUT call for a specific event)
http://localhost:8090/domain/events/{pageNumber}( GET call to fetch all events based on the page number specified)

USER:

http://localhost:8090/domain/users/user (post call USER creation)
http://localhost:8090/domain/users/user/{id}( GET call for a specific user)
http://localhost:8090/domain/users/user/{id}( PUT call for a specific user)
http://localhost:8090/domain/users/user/{id}( DELETE call for a specific user)
http://localhost:8090/domain/users/user/{id}/event( POST call registering user to an event)
http://localhost:8090/domain/users( GET call to fetch all users)




