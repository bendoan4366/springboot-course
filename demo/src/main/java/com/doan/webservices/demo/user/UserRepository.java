package com.doan.webservices.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;

//create an interface to connect to JPA repository with user table
public interface UserRepository extends JpaRepository<User, Integer> {

}
