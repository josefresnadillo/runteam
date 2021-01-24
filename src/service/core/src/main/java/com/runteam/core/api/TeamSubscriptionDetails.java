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



@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2021-01-24T20:28:43.552700+01:00[Europe/Madrid]")public class TeamSubscriptionDetails   {
  
  private @Valid Long members;
  private @Valid Long memberships;

  /**
   **/
  public TeamSubscriptionDetails members(Long members) {
    this.members = members;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("members")
  public Long getMembers() {
    return members;
  }

  public void setMembers(Long members) {
    this.members = members;
  }/**
   **/
  public TeamSubscriptionDetails memberships(Long memberships) {
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
    TeamSubscriptionDetails teamSubscriptionDetails = (TeamSubscriptionDetails) o;
    return Objects.equals(this.members, teamSubscriptionDetails.members) &&
        Objects.equals(this.memberships, teamSubscriptionDetails.memberships);
  }

  @Override
  public int hashCode() {
    return Objects.hash(members, memberships);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TeamSubscriptionDetails {\n");
    
    sb.append("    members: ").append(toIndentedString(members)).append("\n");
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

