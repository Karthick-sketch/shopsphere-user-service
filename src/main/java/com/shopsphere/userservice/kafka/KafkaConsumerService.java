package com.shopsphere.userservice.kafka;

import com.shopsphere.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaConsumerService {

  private final UserService userService;

  @KafkaListener(
    topics = "${kafka.topic.user-created}",
    groupId = "${kafka.consumer.group-id}"
  )
  public void handleUserCreatedEvent(UserCreatedEvent event) {
    userService.createUser(event.getData());
  }
}
