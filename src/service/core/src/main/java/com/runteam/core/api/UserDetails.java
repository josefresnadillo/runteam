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



@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2021-01-02T15:41:38.021217+01:00[Europe/Madrid]")public class UserDetails   {
  
  private @Valid String email;
  private @Valid String imageUrl;
  private @Valid String birthday;
  private @Valid String sex;
  private @Valid String city;
  private @Valid String countryCode;
  private @Valid String language;

  /**
   **/
  public UserDetails email(String email) {
    this.email = email;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }/**
   **/
  public UserDetails imageUrl(String imageUrl) {
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
  public UserDetails birthday(String birthday) {
    this.birthday = birthday;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("birthday")
  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }/**
   **/
  public UserDetails sex(String sex) {
    this.sex = sex;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("sex")
  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }/**
   **/
  public UserDetails city(String city) {
    this.city = city;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("city")
  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }/**
   **/
  public UserDetails countryCode(String countryCode) {
    this.countryCode = countryCode;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("countryCode")
  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }/**
   **/
  public UserDetails language(String language) {
    this.language = language;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("language")
  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserDetails userDetails = (UserDetails) o;
    return Objects.equals(this.email, userDetails.email) &&
        Objects.equals(this.imageUrl, userDetails.imageUrl) &&
        Objects.equals(this.birthday, userDetails.birthday) &&
        Objects.equals(this.sex, userDetails.sex) &&
        Objects.equals(this.city, userDetails.city) &&
        Objects.equals(this.countryCode, userDetails.countryCode) &&
        Objects.equals(this.language, userDetails.language);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, imageUrl, birthday, sex, city, countryCode, language);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserDetails {\n");
    
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    imageUrl: ").append(toIndentedString(imageUrl)).append("\n");
    sb.append("    birthday: ").append(toIndentedString(birthday)).append("\n");
    sb.append("    sex: ").append(toIndentedString(sex)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    countryCode: ").append(toIndentedString(countryCode)).append("\n");
    sb.append("    language: ").append(toIndentedString(language)).append("\n");
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

