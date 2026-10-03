package com.shopsphere.userservice.kafka;

import com.shopsphere.userservice.dto.UserCreatedData;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreatedEvent {

  private UUID eventId;
  private String eventType;
  private Instant occurredAt;
  private UserCreatedData data;
}
