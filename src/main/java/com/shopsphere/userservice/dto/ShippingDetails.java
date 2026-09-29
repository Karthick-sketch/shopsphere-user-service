package com.shopsphere.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShippingDetails {

  private String name;
  private String phoneNumber;
  private String shippingAddress;
}
