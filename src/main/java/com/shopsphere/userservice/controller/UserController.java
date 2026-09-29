package com.shopsphere.userservice.controller;

import com.shopsphere.userservice.dto.ShippingDetails;
import com.shopsphere.userservice.entity.User;
import com.shopsphere.userservice.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping
  public ResponseEntity<List<User>> getAll() {
    return ResponseEntity.ok(userService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getById(@PathVariable Long id) {
    return ResponseEntity.ok(userService.findById(id));
  }

  @GetMapping("/shipping-details/{authUserId}")
  public ResponseEntity<ShippingDetails> getShippingAddress(
    @PathVariable Long authUserId
  ) {
    return ResponseEntity.ok(userService.getShippingAddress(authUserId));
  }

  @PostMapping
  public ResponseEntity<User> create(@RequestBody User user) {
    return ResponseEntity.status(HttpStatus.CREATED).body(
      userService.create(user)
    );
  }

  @PutMapping("/{id}")
  public ResponseEntity<User> update(
    @PathVariable Long id,
    @RequestBody User user
  ) {
    return ResponseEntity.ok(userService.update(id, user));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    userService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
