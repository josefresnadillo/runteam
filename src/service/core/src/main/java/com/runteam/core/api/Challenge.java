package com.runteam.core.api;

import com.runteam.core.api.ChallengeDetails;
import com.runteam.core.api.ChallengeGoal;
import com.runteam.core.api.ChallengeSubscriptionDetails;
import com.runteam.core.api.Privacy;
import com.runteam.core.api.Statistics;
import com.runteam.core.api.Status;
import com.runteam.core.api.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import javax.validation.Valid;

import io.swagger.annotations.*;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;



@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2021-01-24T20:28:43.552700+01:00[Europe/Madrid]")public class Challenge   {
  
  private @Valid String id;
  private @Valid String name;
  private @Valid String displayName;
  private @Valid User owner;
  private @Valid String creationDate;
  private @Valid ChallengeDetails details;
  private @Valid ChallengeGoal goal;
  private @Valid Status status;
  private @Valid Privacy privacy;
  private @Valid ChallengeSubscriptionDetails subscriptionDetails;
  private @Valid Statistics statistics;

  /**
   **/
  public Challenge id(String id) {
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
  public Challenge name(String name) {
    this.name = name;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }/**
   **/
  public Challenge displayName(String displayName) {
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
  public Challenge owner(User owner) {
    this.owner = owner;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("owner")
  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }/**
   **/
  public Challenge creationDate(String creationDate) {
    this.creationDate = creationDate;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("creationDate")
  public String getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(String creationDate) {
    this.creationDate = creationDate;
  }/**
   **/
  public Challenge details(ChallengeDetails details) {
    this.details = details;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("details")
  public ChallengeDetails getDetails() {
    return details;
  }

  public void setDetails(ChallengeDetails details) {
    this.details = details;
  }/**
   **/
  public Challenge goal(ChallengeGoal goal) {
    this.goal = goal;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("goal")
  public ChallengeGoal getGoal() {
    return goal;
  }

  public void setGoal(ChallengeGoal goal) {
    this.goal = goal;
  }/**
   **/
  public Challenge status(Status status) {
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
  public Challenge privacy(Privacy privacy) {
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
  public Challenge subscriptionDetails(ChallengeSubscriptionDetails subscriptionDetails) {
    this.subscriptionDetails = subscriptionDetails;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("subscriptionDetails")
  public ChallengeSubscriptionDetails getSubscriptionDetails() {
    return subscriptionDetails;
  }

  public void setSubscriptionDetails(ChallengeSubscriptionDetails subscriptionDetails) {
    this.subscriptionDetails = subscriptionDetails;
  }/**
   **/
  public Challenge statistics(Statistics statistics) {
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
    Challenge challenge = (Challenge) o;
    return Objects.equals(this.id, challenge.id) &&
        Objects.equals(this.name, challenge.name) &&
        Objects.equals(this.displayName, challenge.displayName) &&
        Objects.equals(this.owner, challenge.owner) &&
        Objects.equals(this.creationDate, challenge.creationDate) &&
        Objects.equals(this.details, challenge.details) &&
        Objects.equals(this.goal, challenge.goal) &&
        Objects.equals(this.status, challenge.status) &&
        Objects.equals(this.privacy, challenge.privacy) &&
        Objects.equals(this.subscriptionDetails, challenge.subscriptionDetails) &&
        Objects.equals(this.statistics, challenge.statistics);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, displayName, owner, creationDate, details, goal, status, privacy, subscriptionDetails, statistics);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Challenge {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    displayName: ").append(toIndentedString(displayName)).append("\n");
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
    sb.append("    details: ").append(toIndentedString(details)).append("\n");
    sb.append("    goal: ").append(toIndentedString(goal)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    privacy: ").append(toIndentedString(privacy)).append("\n");
    sb.append("    subscriptionDetails: ").append(toIndentedString(subscriptionDetails)).append("\n");
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

