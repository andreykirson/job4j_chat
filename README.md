

## RESTfull Spring Boot API project

This project represents the Spring Boot REST full API. 

##### Functionality:
* CRUD operation for a user
* CRUD operation for a chat room
* CRUD operation for a message

##### Used technologies:
* JWT for authentication and authorization
* Spring Boot as a web framework
* Spring Data as framework for data base

#### End points:
 
##### Login by email and password:

POST /chat/login

JSON <br/>

{"email": "example@mail.ru"}

Create a new room by given person:

POST /person/1/room

{"title":  "new room", <br/>
"createdDate": "2021-02-21T07:40:09.143+00:00"}

##### Send message by given person in given room:

POST /person/{personId}/room/{roomId}/chat/send

JSON <br/>

{"content": "new message", <br/>
"createdDate": "2021-02-22T07:39:09.143+00:00"}

##### Get all messages in given room:

GET /person/{personId}/room/{roomId}/message/allmsginroom

##### Delete msg in given room by given person

Delete /person/{personId}/room/{roomId}/message/{msgId} 

##### Sign-up by a new person:

POST /person/sign-up

JSON <br/>
{
"name": "new name", <br/>
"email": "newemail@mail.com", <br/>
"password": "new password"
}
