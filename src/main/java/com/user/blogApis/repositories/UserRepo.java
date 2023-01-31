package com.user.blogApis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.blogApis.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
