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



@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2021-01-30T21:50:04.103897+01:00[Europe/Madrid]")public class PersonalBest   {
  
  private @Valid Long best1kInSeconds;
  private @Valid Long best5kInSeconds;
  private @Valid Long best10kInSeconds;
  private @Valid Long bestMMInSeconds;
  private @Valid Long bestMInSeconds;

  /**
   **/
  public PersonalBest best1kInSeconds(Long best1kInSeconds) {
    this.best1kInSeconds = best1kInSeconds;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("best1kInSeconds")
  public Long getBest1kInSeconds() {
    return best1kInSeconds;
  }

  public void setBest1kInSeconds(Long best1kInSeconds) {
    this.best1kInSeconds = best1kInSeconds;
  }/**
   **/
  public PersonalBest best5kInSeconds(Long best5kInSeconds) {
    this.best5kInSeconds = best5kInSeconds;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("best5kInSeconds")
  public Long getBest5kInSeconds() {
    return best5kInSeconds;
  }

  public void setBest5kInSeconds(Long best5kInSeconds) {
    this.best5kInSeconds = best5kInSeconds;
  }/**
   **/
  public PersonalBest best10kInSeconds(Long best10kInSeconds) {
    this.best10kInSeconds = best10kInSeconds;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("best10kInSeconds")
  public Long getBest10kInSeconds() {
    return best10kInSeconds;
  }

  public void setBest10kInSeconds(Long best10kInSeconds) {
    this.best10kInSeconds = best10kInSeconds;
  }/**
   **/
  public PersonalBest bestMMInSeconds(Long bestMMInSeconds) {
    this.bestMMInSeconds = bestMMInSeconds;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("bestMMInSeconds")
  public Long getBestMMInSeconds() {
    return bestMMInSeconds;
  }

  public void setBestMMInSeconds(Long bestMMInSeconds) {
    this.bestMMInSeconds = bestMMInSeconds;
  }/**
   **/
  public PersonalBest bestMInSeconds(Long bestMInSeconds) {
    this.bestMInSeconds = bestMInSeconds;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("bestMInSeconds")
  public Long getBestMInSeconds() {
    return bestMInSeconds;
  }

  public void setBestMInSeconds(Long bestMInSeconds) {
    this.bestMInSeconds = bestMInSeconds;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PersonalBest personalBest = (PersonalBest) o;
    return Objects.equals(this.best1kInSeconds, personalBest.best1kInSeconds) &&
        Objects.equals(this.best5kInSeconds, personalBest.best5kInSeconds) &&
        Objects.equals(this.best10kInSeconds, personalBest.best10kInSeconds) &&
        Objects.equals(this.bestMMInSeconds, personalBest.bestMMInSeconds) &&
        Objects.equals(this.bestMInSeconds, personalBest.bestMInSeconds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(best1kInSeconds, best5kInSeconds, best10kInSeconds, bestMMInSeconds, bestMInSeconds);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PersonalBest {\n");
    
    sb.append("    best1kInSeconds: ").append(toIndentedString(best1kInSeconds)).append("\n");
    sb.append("    best5kInSeconds: ").append(toIndentedString(best5kInSeconds)).append("\n");
    sb.append("    best10kInSeconds: ").append(toIndentedString(best10kInSeconds)).append("\n");
    sb.append("    bestMMInSeconds: ").append(toIndentedString(bestMMInSeconds)).append("\n");
    sb.append("    bestMInSeconds: ").append(toIndentedString(bestMInSeconds)).append("\n");
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

