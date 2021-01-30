package com.runteam.core.api;

import com.runteam.core.api.PersonalBest;
import com.runteam.core.api.Privacy;
import com.runteam.core.api.Statistics;
import com.runteam.core.api.Status;
import com.runteam.core.api.UserDetails;
import com.runteam.core.api.UserSubscriptionDetails;
import com.runteam.core.api.UserSubscriptionType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import javax.validation.Valid;

import io.swagger.annotations.*;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;



@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2021-01-30T21:50:04.103897+01:00[Europe/Madrid]")public class User   {
  
  private @Valid String id;
  private @Valid String username;
  private @Valid String displayName;
  private @Valid String creationDate;
  private @Valid UserDetails details;
  private @Valid Status status;
  private @Valid Privacy privacy;
  private @Valid UserSubscriptionType subscriptionType;
  private @Valid UserSubscriptionDetails subscriptionDetails;
  private @Valid PersonalBest personalBest;
  private @Valid Statistics statistics;

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
   * 2021/03/19
   **/
  public User creationDate(String creationDate) {
    this.creationDate = creationDate;
    return this;
  }

  

  
  @ApiModelProperty(value = "2021/03/19")
  @JsonProperty("creationDate")
  public String getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(String creationDate) {
    this.creationDate = creationDate;
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
   **/
  public User status(Status status) {
    this.status = status;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("status")
  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
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
   **/
  public User subscriptionType(UserSubscriptionType subscriptionType) {
    this.subscriptionType = subscriptionType;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("subscriptionType")
  public UserSubscriptionType getSubscriptionType() {
    return subscriptionType;
  }

  public void setSubscriptionType(UserSubscriptionType subscriptionType) {
    this.subscriptionType = subscriptionType;
  }/**
   **/
  public User subscriptionDetails(UserSubscriptionDetails subscriptionDetails) {
    this.subscriptionDetails = subscriptionDetails;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("subscriptionDetails")
  public UserSubscriptionDetails getSubscriptionDetails() {
    return subscriptionDetails;
  }

  public void setSubscriptionDetails(UserSubscriptionDetails subscriptionDetails) {
    this.subscriptionDetails = subscriptionDetails;
  }/**
   **/
  public User personalBest(PersonalBest personalBest) {
    this.personalBest = personalBest;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("personalBest")
  public PersonalBest getPersonalBest() {
    return personalBest;
  }

  public void setPersonalBest(PersonalBest personalBest) {
    this.personalBest = personalBest;
  }/**
   **/
  public User statistics(Statistics statistics) {
    this.statistics = statistics;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("statistics")
  public Statistics getStatistics() {
    return statistics;
  }

  public void setStatistics(Statistics statistics) {
    this.statistics = statistics;
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
        Objects.equals(this.creationDate, user.creationDate) &&
        Objects.equals(this.details, user.details) &&
        Objects.equals(this.status, user.status) &&
        Objects.equals(this.privacy, user.privacy) &&
        Objects.equals(this.subscriptionType, user.subscriptionType) &&
        Objects.equals(this.subscriptionDetails, user.subscriptionDetails) &&
        Objects.equals(this.personalBest, user.personalBest) &&
        Objects.equals(this.statistics, user.statistics);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, displayName, creationDate, details, status, privacy, subscriptionType, subscriptionDetails, personalBest, statistics);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    displayName: ").append(toIndentedString(displayName)).append("\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
    sb.append("    details: ").append(toIndentedString(details)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    privacy: ").append(toIndentedString(privacy)).append("\n");
    sb.append("    subscriptionType: ").append(toIndentedString(subscriptionType)).append("\n");
    sb.append("    subscriptionDetails: ").append(toIndentedString(subscriptionDetails)).append("\n");
    sb.append("    personalBest: ").append(toIndentedString(personalBest)).append("\n");
    sb.append("    statistics: ").append(toIndentedString(statistics)).append("\n");
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

