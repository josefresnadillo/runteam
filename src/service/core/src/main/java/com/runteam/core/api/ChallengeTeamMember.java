package com.runteam.core.api;

import com.runteam.core.api.Statistics;
import com.runteam.core.api.Status;
import com.runteam.core.api.Team;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import javax.validation.Valid;

import io.swagger.annotations.*;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;



@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2021-01-30T21:50:04.103897+01:00[Europe/Madrid]")public class ChallengeTeamMember   {
  
  private @Valid String id;
  private @Valid Team team;
  private @Valid String creationDate;
  private @Valid Status status;
  private @Valid Statistics statistics;

  /**
   **/
  public ChallengeTeamMember id(String id) {
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
  public ChallengeTeamMember team(Team team) {
    this.team = team;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("team")
  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }/**
   **/
  public ChallengeTeamMember creationDate(String creationDate) {
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
  public ChallengeTeamMember status(Status status) {
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
  public ChallengeTeamMember statistics(Statistics statistics) {
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
    ChallengeTeamMember challengeTeamMember = (ChallengeTeamMember) o;
    return Objects.equals(this.id, challengeTeamMember.id) &&
        Objects.equals(this.team, challengeTeamMember.team) &&
        Objects.equals(this.creationDate, challengeTeamMember.creationDate) &&
        Objects.equals(this.status, challengeTeamMember.status) &&
        Objects.equals(this.statistics, challengeTeamMember.statistics);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, team, creationDate, status, statistics);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChallengeTeamMember {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    team: ").append(toIndentedString(team)).append("\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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

