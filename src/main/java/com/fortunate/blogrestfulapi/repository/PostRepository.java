package com.fortunate.blogrestfulapi.repository;

import com.fortunate.blogrestfulapi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitleContainingIgnoreCase(String keyword);
}
