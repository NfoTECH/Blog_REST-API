package com.fortunate.blogrestfulapi.repository;

import com.fortunate.blogrestfulapi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
