package com.rhb.api.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;

/**
 * Flash Mapping Field &lt;Body Response&gt;
 */
@Schema(description = "Flash Mapping Field <Body Response>")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-12-14T03:56:16.443398Z[GMT]")


public class CCStatusResBody {
  @JsonProperty("workflowDate")
  private String workflowDate;

  public String getApplicationStatus() {
    return applicationStatus;
  }

  public void setApplicationStatus(String applicationStatus) {
    this.applicationStatus = applicationStatus;
  }

  @JsonProperty("applicationStatus")
  private String applicationStatus;

  @JsonProperty("appDate")
  private String appDate;

  @JsonProperty("workflowDescription")
  private String workflowDesc;

  @JsonProperty("workflowComment")
  private String workflowComment;


  public String getAppDate() {
    return appDate;
  }

  public void setAppDate(String appDate) {
    this.appDate = appDate;
  }

  public String getWorkflowDate() {
    return workflowDate;
  }

  public void setWorkflowDate(String workflowDate) {
    this.workflowDate = workflowDate;
  }

  public String getWorkflowComment() {
    return workflowComment;
  }

  public void setWorkflowComment(String workflowComment) {
    this.workflowComment = workflowComment;
  }

  public String getWorkflowDesc() {
    return workflowDesc;
  }

  public void setWorkflowDesc(String workflowDesc) {
    this.workflowDesc = workflowDesc;
  }


  @JsonProperty("applicationNumber")
  private String appNumber = null;

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  @JsonProperty("customerName")
  private String customerName = null;


  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  @JsonProperty("customerID")
  private String customerId = null;
  @JsonProperty("customerMobilePhoneNo")
  private String customerMobilePhoneNo = null;

  @JsonProperty("workflowStatus")
  private String workflowStatus = null;

  @JsonProperty("systemDecision")
  private String systemDecision = null;

  public CCStatusResBody appNumber(String appNumber) {
    this.appNumber = appNumber;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CCStatusResBody that = (CCStatusResBody) o;
    return Objects.equals(workflowDate, that.workflowDate) && Objects.equals(applicationStatus, that.applicationStatus) && Objects.equals(appDate, that.appDate) && Objects.equals(workflowDesc, that.workflowDesc) && Objects.equals(workflowComment, that.workflowComment) && Objects.equals(appNumber, that.appNumber) && Objects.equals(customerName, that.customerName) && Objects.equals(customerId, that.customerId) && Objects.equals(customerMobilePhoneNo, that.customerMobilePhoneNo) && Objects.equals(workflowStatus, that.workflowStatus) && Objects.equals(systemDecision, that.systemDecision);
  }

  @Override
  public int hashCode() {
    return Objects.hash(workflowDate, applicationStatus, appDate, workflowDesc, workflowComment, appNumber, customerName, customerId, customerMobilePhoneNo, workflowStatus, systemDecision);
  }

  /**
   * Application Number
   * @return appNumber
   **/
  @Schema(required = true, description = "Application Number")
      @NotNull

    public String getAppNumber() {
    return appNumber;
  }

  public void setAppNumber(String appNumber) {
    this.appNumber = appNumber;
  }





  public CCStatusResBody customerMobilePhoneNo(String customerMobilePhoneNo) {
    this.customerMobilePhoneNo = customerMobilePhoneNo;
    return this;
  }

  /**
   * Customer Mobile Phone No
   * @return customerMobilePhoneNo
   **/
  @Schema(description = "Customer Mobile Phone No")
  
    public String getCustomerMobilePhoneNo() {
    return customerMobilePhoneNo;
  }

  public void setCustomerMobilePhoneNo(String customerMobilePhoneNo) {
    this.customerMobilePhoneNo = customerMobilePhoneNo;
  }

  public CCStatusResBody workflowStatus(String workflowStatus) {
    this.workflowStatus = workflowStatus;
    return this;
  }

  /**
   * WorkflowStatus
   * @return workflowStatus
   **/
  @Schema(description = "WorkflowStatus")
  
    public String getWorkflowStatus() {
    return workflowStatus;
  }

  public void setWorkflowStatus(String workflowStatus) {
    this.workflowStatus = workflowStatus;
  }


  public CCStatusResBody systemDecision(String systemDecision) {
    this.systemDecision = systemDecision;
    return this;
  }

  /**
   * System Decision
   * @return systemDecision
   **/
  @Schema(description = "System Decision")
  
    public String getSystemDecision() {
    return systemDecision;
  }

  public void setSystemDecision(String systemDecision) {
    this.systemDecision = systemDecision;
  }


  @Override
  public String toString() {
    return "PFStatusResBody{" +
            "workflowDate='" + workflowDate + '\'' +
            ", appDate='" + appDate + '\'' +
            ", applicationStatus='" + applicationStatus + '\'' +
            ", workflowDesc='" + workflowDesc + '\'' +
            ", workflowComment='" + workflowComment + '\'' +
            ", appNumber='" + appNumber + '\'' +
            ", customerName='" + customerName + '\'' +
            ", customerId='" + customerId + '\'' +
            ", customerMobilePhoneNo='" + customerMobilePhoneNo + '\'' +
            ", workflowStatus='" + workflowStatus + '\'' +
            ", systemDecision='" + systemDecision +
            '}';
  }
}
