package com.lucas.springbootmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lucas.springbootmongo.domain.Post;
import com.lucas.springbootmongo.domain.User;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}
