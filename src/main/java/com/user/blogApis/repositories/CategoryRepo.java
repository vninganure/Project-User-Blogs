package com.user.blogApis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.blogApis.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
