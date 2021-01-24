package com.runteam.core.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

import io.swagger.annotations.*;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;



@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2021-01-24T20:28:43.552700+01:00[Europe/Madrid]")public class ChallengeDetails   {
  
  private @Valid String imageUrl;
  private @Valid List<String> tags = new ArrayList<String>();

  /**
   **/
  public ChallengeDetails imageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("imageUrl")
  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }/**
   **/
  public ChallengeDetails tags(List<String> tags) {
    this.tags = tags;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("tags")
  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChallengeDetails challengeDetails = (ChallengeDetails) o;
    return Objects.equals(this.imageUrl, challengeDetails.imageUrl) &&
        Objects.equals(this.tags, challengeDetails.tags);
  }

  @Override
  public int hashCode() {
    return Objects.hash(imageUrl, tags);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChallengeDetails {\n");
    
    sb.append("    imageUrl: ").append(toIndentedString(imageUrl)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
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

