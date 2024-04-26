package com.rhb.api.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Body
 */
@Schema(description = "Body")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-26T06:53:14.739924Z[GMT]")


public class CCReqBody   {
  @JsonProperty("primaryID")
  private String primaryID = null;

  @JsonProperty("fromDate")
  private String fromDate = null;

  @JsonProperty("toDate")
  private String toDate = null;

  @JsonProperty("externalRefID")
  private String externalRefID = null;

  public CCReqBody primaryID(String primaryID) {
    this.primaryID = primaryID;
    return this;
  }

  /**
   * Primary ID
   * @return primaryID
   **/
  @Schema(required = true, description = "Primary ID")
      @NotNull

    public String getPrimaryID() {
    return primaryID;
  }

  public void setPrimaryID(String primaryID) {
    this.primaryID = primaryID;
  }

  public CCReqBody fromDate(String fromDate) {
    this.fromDate = fromDate;
    return this;
  }

  /**
   * From Date
   * @return fromDate
   **/
  @Schema(required = true, description = "From Date")
      @NotNull

    @Valid
    public String getFromDate() {
    return fromDate;
  }

  public void setFromDate(String fromDate) {
    this.fromDate = fromDate;
  }

  public CCReqBody toDate(String toDate) {
    this.toDate = toDate;
    return this;
  }

  /**
   * To Date
   * @return toDate
   **/
  @Schema(required = true, description = "To Date")
      @NotNull

    @Valid
    public String getToDate() {
    return toDate;
  }

  public void setToDate(String toDate) {
    this.toDate = toDate;
  }

  public CCReqBody externalRefID(String externalRefID) {
    this.externalRefID = externalRefID;
    return this;
  }

  /**
   * External Ref Number
   * @return externalRefID
   **/
  @Schema(required = true, description = "External Ref Number")
      @NotNull

    public String getExternalRefID() {
    return externalRefID;
  }

  public void setExternalRefID(String externalRefID) {
    this.externalRefID = externalRefID;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CCReqBody ccReqBody = (CCReqBody) o;
    return Objects.equals(this.primaryID, ccReqBody.primaryID) &&
        Objects.equals(this.fromDate, ccReqBody.fromDate) &&
        Objects.equals(this.toDate, ccReqBody.toDate) &&
        Objects.equals(this.externalRefID, ccReqBody.externalRefID);
  }

  @Override
  public int hashCode() {
    return Objects.hash(primaryID, fromDate, toDate, externalRefID);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CCReqBody {\n");
    
    sb.append("    primaryID: ").append(toIndentedString(primaryID)).append("\n");
    sb.append("    fromDate: ").append(toIndentedString(fromDate)).append("\n");
    sb.append("    toDate: ").append(toIndentedString(toDate)).append("\n");
    sb.append("    externalRefID: ").append(toIndentedString(externalRefID)).append("\n");
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
