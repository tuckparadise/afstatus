package com.rhb.api.models;

import java.util.List;
import java.util.Objects;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.xml.bind.annotation.XmlElement;


/**
 * Flash Mapping Field &lt;Body Response&gt;
 */
@Schema(description = "Flash Mapping Field <Body Response>")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-12-14T03:56:16.443398Z[GMT]")


public class CCFlashResBody {
    @XmlElement(name = "WorkflowDate")
    private String workflowDate;

    @XmlElement(name = "AppDate")
    private String appDate;

    public String getAppDate() {
        return appDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setAppDate(String appDate) {
        this.appDate = appDate;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    @XmlElement(name = "ApplicationNo")
    private String appNumber;

    public String getAppNumber() {
        return appNumber;
    }

    public void setAppNumber(String appNumber) {
        this.appNumber = appNumber;
    }

    @XmlElement(name = "AppStatus")
    private String applicationStatus;



    @XmlElement(name = "WorkflowComment")
    private String workflowComment;


    @XmlElement(name = "WorkflowStatus")
    private String workflowStatus = null;



    @XmlElement(name = "SystemDecision")
    private String systemDecision = null;

    @XmlElement(name = "CustomerName")
    private String customerName = null;
    @XmlElement(name = "CustomerID")
    private String customerId = null;



    @XmlElement(name = "CustomerMobilePhoneNo")
    private String customerMobilePhoneNo = null;


    @XmlElement(name = "WorkflowDescription")
    private String workflowDesc;

    @XmlElement(name = "FaclityList")
    private List<String> facilityList;

    public List<String> getFacilityList() {
        return facilityList;
    }

    public void setFacilityList(List<String> facilityList) {
        this.facilityList = facilityList;
    }

    public CCFlashResBody appNumber(String appNumber) {
        this.appNumber = appNumber;
        return this;
    }





    public CCFlashResBody customerMobilePhoneNo(String customerMobilePhoneNo) {
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

    public CCFlashResBody workflowStatus(String workflowStatus) {
        this.workflowStatus = workflowStatus;
        return this;
    }

    /**
     * WorkflowStatus
     * @return workflowStatus
     **/


    public CCFlashResBody systemDecision(String systemDecision) {
        this.systemDecision = systemDecision;
        return this;
    }

    public String getWorkflowDate() {
        return workflowDate;
    }

    public void setWorkflowDate(String workflowDate) {
        this.workflowDate = workflowDate;
    }


    public String getApplicationStatus() {
        return applicationStatus;
    }

    public String getWorkflowComment() {
        return workflowComment;
    }

    public void setWorkflowComment(String workflowComment) {
        this.workflowComment = workflowComment;
    }

    public String getWorkflowStatus() {
        return workflowStatus;
    }

    public void setWorkflowStatus(String workflowStatus) {
        this.workflowStatus = workflowStatus;
    }

    public String getSystemDecision() {
        return systemDecision;
    }

    public void setSystemDecision(String systemDecision) {
        this.systemDecision = systemDecision;
    }

    public String getWorkflowDesc() {
        return workflowDesc;
    }



    public void setWorkflowDesc(String workflowDesc) {
        this.workflowDesc = workflowDesc;
    }

    /**
     * System Decision
     * @return systemDecision
     **/


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CCFlashResBody that = (CCFlashResBody) o;
        return Objects.equals(workflowDate, that.workflowDate) && Objects.equals(appNumber, that.appNumber) && Objects.equals(applicationStatus, that.applicationStatus) && Objects.equals(workflowComment, that.workflowComment) && Objects.equals(workflowStatus, that.workflowStatus) && Objects.equals(systemDecision, that.systemDecision) && Objects.equals(customerMobilePhoneNo, that.customerMobilePhoneNo) && Objects.equals(workflowDesc, that.workflowDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workflowDate, applicationStatus, workflowDesc, workflowComment, appNumber, customerMobilePhoneNo, workflowStatus, systemDecision);
    }

    @Override
    public String toString() {
        return "PFStatusResBody{" +
                "workflowDate='" + workflowDate + '\'' +
                ", applicationStatus='" + applicationStatus + '\'' +
                ", workflowDesc='" + workflowDesc + '\'' +
                ", workflowComment='" + workflowComment + '\'' +
                ", appNumber='" + appNumber + '\'' +
                ", customerMobilePhoneNo='" + customerMobilePhoneNo + '\'' +
                ", workflowStatus='" + workflowStatus + '\'' +
                ", systemDecision='" + systemDecision +
                '}';
    }
}
