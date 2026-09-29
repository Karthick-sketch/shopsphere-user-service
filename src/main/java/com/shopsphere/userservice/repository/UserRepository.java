package com.shopsphere.userservice.repository;

import com.shopsphere.userservice.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);

  Optional<User> findByAuthUserId(Long authUserId);

  boolean existsByEmail(String email);
}
