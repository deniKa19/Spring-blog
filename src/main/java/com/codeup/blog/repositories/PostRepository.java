package com.codeup.blog.repositories;

import com.codeup.blog.models.Post;
import org.springframework.data.repository.CrudRepository;

//This comes with findAll, findOne, save, delete methods out of the box.
public interface PostRepository extends CrudRepository<Post, Long> {
}
