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



@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2021-01-24T20:28:43.552700+01:00[Europe/Madrid]")public class Statistics   {
  
  private @Valid Long meters;
  private @Valid Long elevation;
  private @Valid Long seconds;

  /**
   **/
  public Statistics meters(Long meters) {
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
  public Statistics elevation(Long elevation) {
    this.elevation = elevation;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("elevation")
  public Long getElevation() {
    return elevation;
  }

  public void setElevation(Long elevation) {
    this.elevation = elevation;
  }/**
   **/
  public Statistics seconds(Long seconds) {
    this.seconds = seconds;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("seconds")
  public Long getSeconds() {
    return seconds;
  }

  public void setSeconds(Long seconds) {
    this.seconds = seconds;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Statistics statistics = (Statistics) o;
    return Objects.equals(this.meters, statistics.meters) &&
        Objects.equals(this.elevation, statistics.elevation) &&
        Objects.equals(this.seconds, statistics.seconds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(meters, elevation, seconds);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Statistics {\n");
    
    sb.append("    meters: ").append(toIndentedString(meters)).append("\n");
    sb.append("    elevation: ").append(toIndentedString(elevation)).append("\n");
    sb.append("    seconds: ").append(toIndentedString(seconds)).append("\n");
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

