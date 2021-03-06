/*
 * 
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: Cable
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.swagger.marvelapi.services.marvel.model;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * ComicDate
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-10-07T15:57:52.262+01:00")
public class ComicDate {
  @SerializedName("type")
  private String type = null;

  @SerializedName("date")
  private java.util.Date date = null;

  public ComicDate type(String type) {
    this.type = type;
    return this;
  }

   /**
   * A description of the date (e.g. onsale date, FOC date).
   * @return type
  **/
  @ApiModelProperty(value = "A description of the date (e.g. onsale date, FOC date).")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public ComicDate date(java.util.Date date) {
    this.date = date;
    return this;
  }

   /**
   * The date.
   * @return date
  **/
  @ApiModelProperty(value = "The date.")
  public java.util.Date getDate() {
    return date;
  }

  public void setDate(java.util.Date date) {
    this.date = date;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ComicDate comicDate = (ComicDate) o;
    return Objects.equals(this.type, comicDate.type) &&
        Objects.equals(this.date, comicDate.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, date);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ComicDate {\n");
    
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

