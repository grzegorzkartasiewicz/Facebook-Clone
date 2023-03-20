# Facebook-Clone
Simple Spring Boot web application with common Facebook features like creating user,post or comment, logging and singing up.
Application uses H2 database.
To start application run FacebookCloneApplication.java then open your browser and go to localhost:8080.
Feel free to try it out. 
Any feedback is welcome.


To use my version of database you need to change application properties from:
spring.datasource.url= jdbc:h2:file:C:/Users/nardi/OneDrive/Pulpit/programowanie_java/Facebook-Clone/fb_clone-db to
spring.datasource.url= jdbc:h2:file:{your path}/fb_clone-db
