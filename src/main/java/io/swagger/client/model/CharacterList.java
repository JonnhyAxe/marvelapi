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


package io.swagger.client.model;

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
 * CharacterList
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-10-07T15:57:52.262+01:00")
public class CharacterList {
  @SerializedName("available")
  private Integer available = null;

  @SerializedName("returned")
  private Integer returned = null;

  @SerializedName("collectionURI")
  private String collectionURI = null;

  @SerializedName("items")
  private java.util.List items = null;

  public CharacterList available(Integer available) {
    this.available = available;
    return this;
  }

   /**
   * The number of total available characters in this list. Will always be greater than or equal to the \&quot;returned\&quot; value.
   * @return available
  **/
  @ApiModelProperty(value = "The number of total available characters in this list. Will always be greater than or equal to the \"returned\" value.")
  public Integer getAvailable() {
    return available;
  }

  public void setAvailable(Integer available) {
    this.available = available;
  }

  public CharacterList returned(Integer returned) {
    this.returned = returned;
    return this;
  }

   /**
   * The number of characters returned in this collection (up to 20).
   * @return returned
  **/
  @ApiModelProperty(value = "The number of characters returned in this collection (up to 20).")
  public Integer getReturned() {
    return returned;
  }

  public void setReturned(Integer returned) {
    this.returned = returned;
  }

  public CharacterList collectionURI(String collectionURI) {
    this.collectionURI = collectionURI;
    return this;
  }

   /**
   * The path to the full list of characters in this collection.
   * @return collectionURI
  **/
  @ApiModelProperty(value = "The path to the full list of characters in this collection.")
  public String getCollectionURI() {
    return collectionURI;
  }

  public void setCollectionURI(String collectionURI) {
    this.collectionURI = collectionURI;
  }

  public CharacterList items(java.util.List items) {
    this.items = items;
    return this;
  }

   /**
   * The list of returned characters in this collection.
   * @return items
  **/
  @ApiModelProperty(value = "The list of returned characters in this collection.")
  public java.util.List getItems() {
    return items;
  }

  public void setItems(java.util.List items) {
    this.items = items;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CharacterList characterList = (CharacterList) o;
    return Objects.equals(this.available, characterList.available) &&
        Objects.equals(this.returned, characterList.returned) &&
        Objects.equals(this.collectionURI, characterList.collectionURI) &&
        Objects.equals(this.items, characterList.items);
  }

  @Override
  public int hashCode() {
    return Objects.hash(available, returned, collectionURI, items);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CharacterList {\n");
    
    sb.append("    available: ").append(toIndentedString(available)).append("\n");
    sb.append("    returned: ").append(toIndentedString(returned)).append("\n");
    sb.append("    collectionURI: ").append(toIndentedString(collectionURI)).append("\n");
    sb.append("    items: ").append(toIndentedString(items)).append("\n");
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

