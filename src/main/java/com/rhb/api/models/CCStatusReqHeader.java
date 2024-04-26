package com.rhb.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;
import javax.validation.constraints.*;
import org.springframework.validation.annotation.Validated;

/**
 * EAI Mapping Field &lt;Header Request&gt;
 */
@Schema(description = "EAI Mapping Field <Header Request>")
@Validated

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-12-15T01:00:23.343319Z[GMT]")

public class CCStatusReqHeader {
  @JsonProperty("messageVersionNo")
  private Double messageVersionNo = 1.0d;

  @JsonProperty("sourceID")
  private String sourceID = "API";

  @JsonProperty("messageRefNo")
  private String messageRefNo = null;

  @JsonProperty("loginID")
  private Integer loginID = null;

  @JsonProperty("pan")
  private String pan = null;

  @JsonProperty("extTransactionCode")
  private String extTransactionCode = "REAIDCI1";

  @JsonProperty("dplName")
  private String dplName = null;

  @JsonProperty("filler")
  private String filler = null;

  @JsonProperty("recordLength")
  private Integer recordLength = null;

  @JsonProperty("noOfRecords")
  private Integer noOfRecords = null;

  @JsonProperty("transactionDate")
  private Integer transactionDate = null;

  @JsonProperty("transactionTime")
  private Integer transactionTime = null;

  @JsonProperty("destQueueManager")
  private String destQueueManager = null;

  @JsonProperty("destQueue")
  private String destQueue = null;

  @JsonProperty("errorCode")
  private Integer errorCode = null;

  @JsonProperty("systemStatus")
  private String systemStatus = null;

  @JsonProperty("errorMessage")
  private String errorMessage = null;

  @JsonProperty("terminalID")
  private String terminalID = null;

  @JsonProperty("replyRefNo")
  private String replyRefNo = null;

  @JsonProperty("overrideTellerId")
  private Integer overrideTellerId = null;

  public CCStatusReqHeader messageVersionNo(Double messageVersionNo) {
    this.messageVersionNo = messageVersionNo;
    return this;
  }

  /**
   * Message Version No
   * @return messageVersionNo
   **/
  @Schema(required = true, description = "Message Version No")
      @NotNull(message = "{PfAip.messageVersionNo.blank}")

    public Double getMessageVersionNo() {
    return messageVersionNo;
  }

  public void setMessageVersionNo(Double messageVersionNo) {
    this.messageVersionNo = messageVersionNo;
  }

  public CCStatusReqHeader sourceID(String sourceID) {
    this.sourceID = sourceID;
    return this;
  }

  /**
   * Source ID
   * @return sourceID
   **/
  @Schema(required = true, description = "Source ID")
      @NotBlank(message = "{PfAip.sourceID.blank}")

    public String getSourceID() {
    return sourceID;
  }

  public void setSourceID(String sourceID) {
    this.sourceID = sourceID;
  }

  public CCStatusReqHeader messageRefNo(String messageRefNo) {
    this.messageRefNo = messageRefNo;
    return this;
  }

  /**
   * Message Reference No
   * @return messageRefNo
   **/
  @Schema(example = "MGECO000120220611012345000001", required = true, description = "Message Reference No")
      @NotNull(message = "{PfAip.messageRefNo.blank}")

    public String getMessageRefNo() {
    return messageRefNo;
  }

  public void setMessageRefNo(String messageRefNo) {
    this.messageRefNo = messageRefNo;
  }

  public CCStatusReqHeader loginID(Integer loginID) {
    this.loginID = loginID;
    return this;
  }

  /**
   * Login ID
   * @return loginID
   **/
  @Schema(description = "Login ID")
  
    public Integer getLoginID() {
    return loginID;
  }

  public void setLoginID(Integer loginID) {
    this.loginID = loginID;
  }

  public CCStatusReqHeader pan(String pan) {
    this.pan = pan;
    return this;
  }

  /**
   * Pan
   * @return pan
   **/
  @Schema(description = "Pan")
  
    public String getPan() {
    return pan;
  }

  public void setPan(String pan) {
    this.pan = pan;
  }

  public CCStatusReqHeader extTransactionCode(String extTransactionCode) {
    this.extTransactionCode = extTransactionCode;
    return this;
  }

  /**
   * External Transaction Code
   * @return extTransactionCode
   **/
  @Schema(required = true, description = "External Transaction Code")
      @NotNull(message = "{PfAip.extTransactionCode.blank}")

    public String getExtTransactionCode() {
    return extTransactionCode;
  }

  public void setExtTransactionCode(String extTransactionCode) {
    this.extTransactionCode = extTransactionCode;
  }

  public CCStatusReqHeader dplName(String dplName) {
    this.dplName = dplName;
    return this;
  }

  /**
   * Dpl Name 
   * @return dplName
   **/
  @Schema(description = "Dpl Name ")
  
    public String getDplName() {
    return dplName;
  }

  public void setDplName(String dplName) {
    this.dplName = dplName;
  }

  public CCStatusReqHeader filler(String filler) {
    this.filler = filler;
    return this;
  }

  /**
   * Filler
   * @return filler
   **/
  @Schema(description = "Filler")
  
    public String getFiller() {
    return filler;
  }

  public void setFiller(String filler) {
    this.filler = filler;
  }

  public CCStatusReqHeader recordLength(Integer recordLength) {
    this.recordLength = recordLength;
    return this;
  }

  /**
   * Record Length
   * @return recordLength
   **/
  @Schema(description = "Record Length")
  
    public Integer getRecordLength() {
    return recordLength;
  }

  public void setRecordLength(Integer recordLength) {
    this.recordLength = recordLength;
  }

  public CCStatusReqHeader noOfRecords(Integer noOfRecords) {
    this.noOfRecords = noOfRecords;
    return this;
  }

  /**
   * No of records
   * @return noOfRecords
   **/
  @Schema(description = "No of records")
  
    public Integer getNoOfRecords() {
    return noOfRecords;
  }

  public void setNoOfRecords(Integer noOfRecords) {
    this.noOfRecords = noOfRecords;
  }

  public CCStatusReqHeader transactionDate(Integer transactionDate) {
    this.transactionDate = transactionDate;
    return this;
  }

  /**
   * Transaction Date 
   * @return transactionDate
   **/
  @Schema(required = true, description = "Transaction Date ")
      @NotNull(message = "{PfAip.transactionDate.blank}")

    public Integer getTransactionDate() {
    return transactionDate;
  }

  public void setTransactionDate(Integer transactionDate) {
    this.transactionDate = transactionDate;
  }

  public CCStatusReqHeader transactionTime(Integer transactionTime) {
    this.transactionTime = transactionTime;
    return this;
  }

  /**
   * Transaction Time 
   * @return transactionTime
   **/
  @Schema(required = true, description = "Transaction Time ")
      @NotNull(message = "{PfAip.transactionTime.blank}")

    public Integer getTransactionTime() {
    return transactionTime;
  }

  public void setTransactionTime(Integer transactionTime) {
    this.transactionTime = transactionTime;
  }

  public CCStatusReqHeader destQueueManager(String destQueueManager) {
    this.destQueueManager = destQueueManager;
    return this;
  }

  /**
   * Destination Queue Manager
   * @return destQueueManager
   **/
  @Schema(description = "Destination Queue Manager")
  
    public String getDestQueueManager() {
    return destQueueManager;
  }

  public void setDestQueueManager(String destQueueManager) {
    this.destQueueManager = destQueueManager;
  }

  public CCStatusReqHeader destQueue(String destQueue) {
    this.destQueue = destQueue;
    return this;
  }

  /**
   * Destination Queue
   * @return destQueue
   **/
  @Schema(description = "Destination Queue")
  
    public String getDestQueue() {
    return destQueue;
  }

  public void setDestQueue(String destQueue) {
    this.destQueue = destQueue;
  }

  public CCStatusReqHeader errorCode(Integer errorCode) {
    this.errorCode = errorCode;
    return this;
  }

  /**
   * Error Code
   * @return errorCode
   **/
  @Schema(description = "Error Code")
  
    public Integer getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(Integer errorCode) {
    this.errorCode = errorCode;
  }

  public CCStatusReqHeader systemStatus(String systemStatus) {
    this.systemStatus = systemStatus;
    return this;
  }

  /**
   * System Status
   * @return systemStatus
   **/
  @Schema(description = "System Status")
  
    public String getSystemStatus() {
    return systemStatus;
  }

  public void setSystemStatus(String systemStatus) {
    this.systemStatus = systemStatus;
  }

  public CCStatusReqHeader errorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
    return this;
  }

  /**
   * Error Message
   * @return errorMessage
   **/
  @Schema(description = "Error Message")
  
    public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public CCStatusReqHeader terminalID(String terminalID) {
    this.terminalID = terminalID;
    return this;
  }

  /**
   * Terminal ID
   * @return terminalID
   **/
  @Schema(description = "Terminal ID")
  
    public String getTerminalID() {
    return terminalID;
  }

  public void setTerminalID(String terminalID) {
    this.terminalID = terminalID;
  }

  public CCStatusReqHeader replyRefNo(String replyRefNo) {
    this.replyRefNo = replyRefNo;
    return this;
  }

  /**
   * Reply Ref No
   * @return replyRefNo
   **/
  @Schema(description = "Reply Ref No")
  
    public String getReplyRefNo() {
    return replyRefNo;
  }

  public void setReplyRefNo(String replyRefNo) {
    this.replyRefNo = replyRefNo;
  }

  public CCStatusReqHeader overrideTellerId(Integer overrideTellerId) {
    this.overrideTellerId = overrideTellerId;
    return this;
  }

  /**
   * Override Teller Id
   * @return overrideTellerId
   **/
  @Schema(description = "Override Teller Id")
  
    public Integer getOverrideTellerId() {
    return overrideTellerId;
  }

  public void setOverrideTellerId(Integer overrideTellerId) {
    this.overrideTellerId = overrideTellerId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CCStatusReqHeader pfStatusReqHeader = (CCStatusReqHeader) o;
    return Objects.equals(this.messageVersionNo, pfStatusReqHeader.messageVersionNo) &&
        Objects.equals(this.sourceID, pfStatusReqHeader.sourceID) &&
        Objects.equals(this.messageRefNo, pfStatusReqHeader.messageRefNo) &&
        Objects.equals(this.loginID, pfStatusReqHeader.loginID) &&
        Objects.equals(this.pan, pfStatusReqHeader.pan) &&
        Objects.equals(this.extTransactionCode, pfStatusReqHeader.extTransactionCode) &&
        Objects.equals(this.dplName, pfStatusReqHeader.dplName) &&
        Objects.equals(this.filler, pfStatusReqHeader.filler) &&
        Objects.equals(this.recordLength, pfStatusReqHeader.recordLength) &&
        Objects.equals(this.noOfRecords, pfStatusReqHeader.noOfRecords) &&
        Objects.equals(this.transactionDate, pfStatusReqHeader.transactionDate) &&
        Objects.equals(this.transactionTime, pfStatusReqHeader.transactionTime) &&
        Objects.equals(this.destQueueManager, pfStatusReqHeader.destQueueManager) &&
        Objects.equals(this.destQueue, pfStatusReqHeader.destQueue) &&
        Objects.equals(this.errorCode, pfStatusReqHeader.errorCode) &&
        Objects.equals(this.systemStatus, pfStatusReqHeader.systemStatus) &&
        Objects.equals(this.errorMessage, pfStatusReqHeader.errorMessage) &&
        Objects.equals(this.terminalID, pfStatusReqHeader.terminalID) &&
        Objects.equals(this.replyRefNo, pfStatusReqHeader.replyRefNo) &&
        Objects.equals(this.overrideTellerId, pfStatusReqHeader.overrideTellerId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(messageVersionNo, sourceID, messageRefNo, loginID, pan, extTransactionCode, dplName, filler, recordLength, noOfRecords, transactionDate, transactionTime, destQueueManager, destQueue, errorCode, systemStatus, errorMessage, terminalID, replyRefNo, overrideTellerId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PFStatusReqHeader {\n");
    
    sb.append("    messageVersionNo: ").append(toIndentedString(messageVersionNo)).append("\n");
    sb.append("    sourceID: ").append(toIndentedString(sourceID)).append("\n");
    sb.append("    messageRefNo: ").append(toIndentedString(messageRefNo)).append("\n");
    sb.append("    loginID: ").append(toIndentedString(loginID)).append("\n");
    sb.append("    pan: ").append(toIndentedString(pan)).append("\n");
    sb.append("    extTransactionCode: ").append(toIndentedString(extTransactionCode)).append("\n");
    sb.append("    dplName: ").append(toIndentedString(dplName)).append("\n");
    sb.append("    filler: ").append(toIndentedString(filler)).append("\n");
    sb.append("    recordLength: ").append(toIndentedString(recordLength)).append("\n");
    sb.append("    noOfRecords: ").append(toIndentedString(noOfRecords)).append("\n");
    sb.append("    transactionDate: ").append(toIndentedString(transactionDate)).append("\n");
    sb.append("    transactionTime: ").append(toIndentedString(transactionTime)).append("\n");
    sb.append("    destQueueManager: ").append(toIndentedString(destQueueManager)).append("\n");
    sb.append("    destQueue: ").append(toIndentedString(destQueue)).append("\n");
    sb.append("    errorCode: ").append(toIndentedString(errorCode)).append("\n");
    sb.append("    systemStatus: ").append(toIndentedString(systemStatus)).append("\n");
    sb.append("    errorMessage: ").append(toIndentedString(errorMessage)).append("\n");
    sb.append("    terminalID: ").append(toIndentedString(terminalID)).append("\n");
    sb.append("    replyRefNo: ").append(toIndentedString(replyRefNo)).append("\n");
    sb.append("    overrideTellerId: ").append(toIndentedString(overrideTellerId)).append("\n");
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
