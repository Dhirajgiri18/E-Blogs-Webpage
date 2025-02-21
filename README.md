# Spring Boot Blog Demo

## About

This is a demo project for practicing **Spring Boot + Thymeleaf**. 
The idea was to build some basic blogging platform.

It was made using **Spring Boot**, **Spring Security**, **Thymeleaf**, **Spring Data JPA**, **Spring Data REST** and **Docker**. 
Database is in memory **H2**.

There is a login and registration functionality included.

User has his own blog page, where he can add new blog posts. 
Every authenticated user can comment on posts made by other users.
Home page is paginated list of all posts.
Non-authenticated users can see all blog posts, but cannot add new posts or comment.

## Configuration

### Configuration Files

Folder **src/resources/** contains config files for **blog-demo** Spring Boot application.

* **src/resources/application.properties** - main configuration file. Here it is possible to change admin username/password,
as well as change the port number.
