package com.fortunate.blogrestfulapi.repository;

import com.fortunate.blogrestfulapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
