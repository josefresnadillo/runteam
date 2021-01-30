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



@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2021-01-30T21:50:04.103897+01:00[Europe/Madrid]")public class ChallengeGoal   {
  
  private @Valid Long meters;
  private @Valid Long elevationInMeters;
  private @Valid String activeFrom;
  private @Valid String activeTo;

  /**
   **/
  public ChallengeGoal meters(Long meters) {
    this.meters = meters;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("meters")
  public Long getMeters() {
    return meters;
  }

  public void setMeters(Long meters) {
    this.meters = meters;
  }/**
   **/
  public ChallengeGoal elevationInMeters(Long elevationInMeters) {
    this.elevationInMeters = elevationInMeters;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("elevationInMeters")
  public Long getElevationInMeters() {
    return elevationInMeters;
  }

  public void setElevationInMeters(Long elevationInMeters) {
    this.elevationInMeters = elevationInMeters;
  }/**
   **/
  public ChallengeGoal activeFrom(String activeFrom) {
    this.activeFrom = activeFrom;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("activeFrom")
  public String getActiveFrom() {
    return activeFrom;
  }

  public void setActiveFrom(String activeFrom) {
    this.activeFrom = activeFrom;
  }/**
   **/
  public ChallengeGoal activeTo(String activeTo) {
    this.activeTo = activeTo;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("activeTo")
  public String getActiveTo() {
    return activeTo;
  }

  public void setActiveTo(String activeTo) {
    this.activeTo = activeTo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChallengeGoal challengeGoal = (ChallengeGoal) o;
    return Objects.equals(this.meters, challengeGoal.meters) &&
        Objects.equals(this.elevationInMeters, challengeGoal.elevationInMeters) &&
        Objects.equals(this.activeFrom, challengeGoal.activeFrom) &&
        Objects.equals(this.activeTo, challengeGoal.activeTo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(meters, elevationInMeters, activeFrom, activeTo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChallengeGoal {\n");
    
    sb.append("    meters: ").append(toIndentedString(meters)).append("\n");
    sb.append("    elevationInMeters: ").append(toIndentedString(elevationInMeters)).append("\n");
    sb.append("    activeFrom: ").append(toIndentedString(activeFrom)).append("\n");
    sb.append("    activeTo: ").append(toIndentedString(activeTo)).append("\n");
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

