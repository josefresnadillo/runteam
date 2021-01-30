package com.runteam.core.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import javax.validation.Valid;

import io.swagger.annotations.*;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;



@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2021-01-30T21:50:04.103897+01:00[Europe/Madrid]")public class UserSubscriptionDetails   {
  
  private @Valid Long teamsCreated;
  private @Valid Long challengesCreated;
  private @Valid Long memberships;

  /**
   **/
  public UserSubscriptionDetails teamsCreated(Long teamsCreated) {
    this.teamsCreated = teamsCreated;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("teamsCreated")
  public Long getTeamsCreated() {
    return teamsCreated;
  }

  public void setTeamsCreated(Long teamsCreated) {
    this.teamsCreated = teamsCreated;
  }/**
   **/
  public UserSubscriptionDetails challengesCreated(Long challengesCreated) {
    this.challengesCreated = challengesCreated;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("challengesCreated")
  public Long getChallengesCreated() {
    return challengesCreated;
  }

  public void setChallengesCreated(Long challengesCreated) {
    this.challengesCreated = challengesCreated;
  }/**
   **/
  public UserSubscriptionDetails memberships(Long memberships) {
    this.memberships = memberships;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("memberships")
  public Long getMemberships() {
    return memberships;
  }

  public void setMemberships(Long memberships) {
    this.memberships = memberships;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserSubscriptionDetails userSubscriptionDetails = (UserSubscriptionDetails) o;
    return Objects.equals(this.teamsCreated, userSubscriptionDetails.teamsCreated) &&
        Objects.equals(this.challengesCreated, userSubscriptionDetails.challengesCreated) &&
        Objects.equals(this.memberships, userSubscriptionDetails.memberships);
  }

  @Override
  public int hashCode() {
    return Objects.hash(teamsCreated, challengesCreated, memberships);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserSubscriptionDetails {\n");
    
    sb.append("    teamsCreated: ").append(toIndentedString(teamsCreated)).append("\n");
    sb.append("    challengesCreated: ").append(toIndentedString(challengesCreated)).append("\n");
    sb.append("    memberships: ").append(toIndentedString(memberships)).append("\n");
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

