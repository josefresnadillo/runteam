package com.runteam.core.api;

import com.runteam.core.api.Privacy;
import com.runteam.core.api.UserDetails;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import javax.validation.Valid;

import io.swagger.annotations.*;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;



@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2021-01-02T16:39:11.316253+01:00[Europe/Madrid]")public class User   {
  
  private @Valid String id;
  private @Valid String username;
  private @Valid String displayName;
  private @Valid UserDetails details;

public enum StatusEnum {

    ACTIVE(String.valueOf("active")), INACTIVE(String.valueOf("inactive"));


    private String value;

    StatusEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String value) {
        for (StatusEnum b : StatusEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}

  private @Valid StatusEnum status;
  private @Valid Privacy privacy;

public enum SubscriptionTypeEnum {

    BASIC(String.valueOf("basic")), PREMIUM(String.valueOf("premium"));


    private String value;

    SubscriptionTypeEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static SubscriptionTypeEnum fromValue(String value) {
        for (SubscriptionTypeEnum b : SubscriptionTypeEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}

  private @Valid SubscriptionTypeEnum subscriptionType;

  /**
   **/
  public User id(String id) {
    this.id = id;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }/**
   **/
  public User username(String username) {
    this.username = username;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("username")
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }/**
   **/
  public User displayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("displayName")
  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }/**
   **/
  public User details(UserDetails details) {
    this.details = details;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("details")
  public UserDetails getDetails() {
    return details;
  }

  public void setDetails(UserDetails details) {
    this.details = details;
  }/**
   * User Status
   **/
  public User status(StatusEnum status) {
    this.status = status;
    return this;
  }

  

  
  @ApiModelProperty(value = "User Status")
  @JsonProperty("status")
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }/**
   **/
  public User privacy(Privacy privacy) {
    this.privacy = privacy;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("privacy")
  public Privacy getPrivacy() {
    return privacy;
  }

  public void setPrivacy(Privacy privacy) {
    this.privacy = privacy;
  }/**
   * User Subscription type
   **/
  public User subscriptionType(SubscriptionTypeEnum subscriptionType) {
    this.subscriptionType = subscriptionType;
    return this;
  }

  

  
  @ApiModelProperty(value = "User Subscription type")
  @JsonProperty("subscriptionType")
  public SubscriptionTypeEnum getSubscriptionType() {
    return subscriptionType;
  }

  public void setSubscriptionType(SubscriptionTypeEnum subscriptionType) {
    this.subscriptionType = subscriptionType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(this.id, user.id) &&
        Objects.equals(this.username, user.username) &&
        Objects.equals(this.displayName, user.displayName) &&
        Objects.equals(this.details, user.details) &&
        Objects.equals(this.status, user.status) &&
        Objects.equals(this.privacy, user.privacy) &&
        Objects.equals(this.subscriptionType, user.subscriptionType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, displayName, details, status, privacy, subscriptionType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    displayName: ").append(toIndentedString(displayName)).append("\n");
    sb.append("    details: ").append(toIndentedString(details)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    privacy: ").append(toIndentedString(privacy)).append("\n");
    sb.append("    subscriptionType: ").append(toIndentedString(subscriptionType)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }


}

