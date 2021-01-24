package com.runteam.core.api;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Privacy
 */
public enum UserSubscriptionType {
  
  BASIC("BASIC"),
  
  PREMIUM("PREMIUM");

  private String value;

  UserSubscriptionType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static UserSubscriptionType fromValue(String value) {
    for (UserSubscriptionType b : UserSubscriptionType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}


