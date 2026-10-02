package com.shopsphere.userservice.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreatedEvent {

  private Long authUserId;
  private String name;
  private String email;
}
