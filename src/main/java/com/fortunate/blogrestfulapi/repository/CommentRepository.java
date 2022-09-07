package com.fortunate.blogrestfulapi.repository;

import com.fortunate.blogrestfulapi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByCommentContainingIgnoreCase(String keyword);
}
