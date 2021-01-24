package com.runteam.core.api;

import com.runteam.core.api.PersonalBest;
import com.runteam.core.api.Statistics;
import com.runteam.core.api.Status;
import com.runteam.core.api.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.validation.constraints.*;
import javax.validation.Valid;

import io.swagger.annotations.*;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;



@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2021-01-24T20:28:43.552700+01:00[Europe/Madrid]")public class UserTeamMember   {
  
  private @Valid String id;
  private @Valid User user;
  private @Valid Date creationDate;
  private @Valid Status status;
  private @Valid PersonalBest personalBest;
  private @Valid Statistics statistics;

  /**
   **/
  public UserTeamMember id(String id) {
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
  public UserTeamMember user(User user) {
    this.user = user;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("user")
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }/**
   **/
  public UserTeamMember creationDate(Date creationDate) {
    this.creationDate = creationDate;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("creationDate")
  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }/**
   **/
  public UserTeamMember status(Status status) {
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
  public UserTeamMember personalBest(PersonalBest personalBest) {
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
  public UserTeamMember statistics(Statistics statistics) {
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
    UserTeamMember userTeamMember = (UserTeamMember) o;
    return Objects.equals(this.id, userTeamMember.id) &&
        Objects.equals(this.user, userTeamMember.user) &&
        Objects.equals(this.creationDate, userTeamMember.creationDate) &&
        Objects.equals(this.status, userTeamMember.status) &&
        Objects.equals(this.personalBest, userTeamMember.personalBest) &&
        Objects.equals(this.statistics, userTeamMember.statistics);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, user, creationDate, status, personalBest, statistics);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserTeamMember {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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

