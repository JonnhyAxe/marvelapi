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
 * StorySummary
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-10-07T15:57:52.262+01:00")
public class StorySummary {
  @SerializedName("resourceURI")
  private String resourceURI = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("type")
  private String type = null;

  public StorySummary resourceURI(String resourceURI) {
    this.resourceURI = resourceURI;
    return this;
  }

   /**
   * The path to the individual story resource.
   * @return resourceURI
  **/
  @ApiModelProperty(value = "The path to the individual story resource.")
  public String getResourceURI() {
    return resourceURI;
  }

  public void setResourceURI(String resourceURI) {
    this.resourceURI = resourceURI;
  }

  public StorySummary name(String name) {
    this.name = name;
    return this;
  }

   /**
   * The canonical name of the story.
   * @return name
  **/
  @ApiModelProperty(value = "The canonical name of the story.")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public StorySummary type(String type) {
    this.type = type;
    return this;
  }

   /**
   * The type of the story (interior or cover).
   * @return type
  **/
  @ApiModelProperty(value = "The type of the story (interior or cover).")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StorySummary storySummary = (StorySummary) o;
    return Objects.equals(this.resourceURI, storySummary.resourceURI) &&
        Objects.equals(this.name, storySummary.name) &&
        Objects.equals(this.type, storySummary.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(resourceURI, name, type);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StorySummary {\n");
    
    sb.append("    resourceURI: ").append(toIndentedString(resourceURI)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

