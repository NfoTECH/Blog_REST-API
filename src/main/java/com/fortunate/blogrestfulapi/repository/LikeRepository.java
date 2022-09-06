package com.fortunate.blogrestfulapi.repository;

import com.fortunate.blogrestfulapi.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
