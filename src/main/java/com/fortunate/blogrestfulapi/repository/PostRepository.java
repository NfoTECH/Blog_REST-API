package com.fortunate.blogrestfulapi.repository;

import com.fortunate.blogrestfulapi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
