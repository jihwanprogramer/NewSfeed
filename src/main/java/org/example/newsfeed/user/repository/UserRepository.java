package org.example.newsfeed.user.repository;

import jakarta.persistence.Id;
import org.example.newsfeed.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Id> {
}
