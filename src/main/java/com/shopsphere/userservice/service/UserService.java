package com.shopsphere.userservice.service;

import com.shopsphere.userservice.entity.User;
import com.shopsphere.userservice.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User findById(Long id) {
    return userRepository
      .findById(id)
      .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
  }

  public User findByEmail(String email) {
    return userRepository
      .findByEmail(email)
      .orElseThrow(() ->
        new RuntimeException("User not found with email: " + email)
      );
  }

  public User create(User user) {
    if (userRepository.existsByEmail(user.getEmail())) {
      throw new RuntimeException("Email already in use: " + user.getEmail());
    }
    return userRepository.save(user);
  }

  public User update(Long id, User updated) {
    User existing = findById(id);
    existing.setName(updated.getName());
    existing.setEmail(updated.getEmail());
    existing.setRole(updated.getRole());
    return userRepository.save(existing);
  }

  public void delete(Long id) {
    findById(id); // ensure exists before deleting
    userRepository.deleteById(id);
  }
}
