package com.blog.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.api.entities.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
