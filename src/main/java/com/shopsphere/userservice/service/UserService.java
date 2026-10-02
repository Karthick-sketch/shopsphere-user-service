package com.shopsphere.userservice.service;

import com.shopsphere.userservice.dto.ShippingDetails;
import com.shopsphere.userservice.entity.User;
import com.shopsphere.userservice.exception.UserNotFoundException;
import com.shopsphere.userservice.kafka.UserCreatedEvent;
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

  public ShippingDetails getShippingAddress(Long authUserId) {
    User user = userRepository
      .findByAuthUserId(authUserId)
      .orElseThrow(() ->
        new UserNotFoundException(
          "User not found with authUserId: " + authUserId
        )
      );
    return new ShippingDetails(
      user.getName(),
      user.getPhoneNumber(),
      user.getShippingAddress()
    );
  }

  public User create(User user) {
    if (userRepository.existsByEmail(user.getEmail())) {
      throw new RuntimeException("Email already in use: " + user.getEmail());
    }
    return userRepository.save(user);
  }

  public void createUser(UserCreatedEvent event) {
    create(
      User.builder()
        .authUserId(event.getAuthUserId())
        .name(event.getName())
        .email(event.getEmail())
        .build()
    );
  }

  public User update(Long id, User updated) {
    User existing = findById(id);
    existing.setName(updated.getName());
    existing.setEmail(updated.getEmail());
    existing.setPhoneNumber(updated.getPhoneNumber());
    existing.setShippingAddress(updated.getShippingAddress());
    return userRepository.save(existing);
  }

  public void delete(Long id) {
    findById(id); // ensure exists before deleting
    userRepository.deleteById(id);
  }
}
