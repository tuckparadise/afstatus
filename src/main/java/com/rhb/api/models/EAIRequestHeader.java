package com.rhb.api.models;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.validation.annotation.Validated;

@Schema(description = "FLASH Mapping Field <Request>")
@Validated
@XmlRootElement(name = "Header")
@XmlAccessorType(XmlAccessType.FIELD)
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-12-14T03:56:16.443398Z[GMT]")

public class EAIRequestHeader {
    @XmlElement(name = "MsgVerNo")
    private Double messageVersionNo = 1.0d;

    @XmlElement(name = "SourceId")
    private String sourceID = "API";

    @XmlElement(name = "MsgRefNo")
    private String messageRefNo = null;

    @XmlElement(name = "LoginId")
    private String loginID = null;

    @XmlElement(name = "Pan")
    private String pan = null;

    @XmlElement(name = "ExtTrxnCd")
    private String extTransactionCode = "REAIDCI1";

    @XmlElement(name = "DplName")
    private String dplName = null;

    @XmlElement(name = "Filler")
    private String filler = null;

    @XmlElement(name = "Reclen")
    private String recordLength = null;

    @XmlElement(name = "NoOfRec")
    private String noOfRecords = null;

    @XmlElement(name = "TrxnDate")
    private Integer transactionDate = null;

    @XmlElement(name = "TrxnTime")
    private Integer transactionTime = null;

    @XmlElement(name = "DestQueueMgr")
    private String destQueueManager = null;

    @XmlElement(name = "DestQueue")
    private String destQueue = null;

    @XmlElement(name = "ErrCode")
    private String errorCode = null;

    @XmlElement(name = "SystemStatus")
    private String systemStatus = null;

    @XmlElement(name = "ErrMsg")
    private String errorMessage = null;

    @XmlElement(name = "TerminalId")
    private String terminalID = null;

    @XmlElement(name = "RepRefNo")
    private String replyRefNo = null;

    public Double getMessageVersionNo() {
        return messageVersionNo;
    }

    public void setMessageVersionNo(Double messageVersionNo) {
        this.messageVersionNo = messageVersionNo;
    }

    public String getSourceID() {
        return sourceID;
    }

    public void setSourceID(String sourceID) {
        this.sourceID = sourceID;
    }

    public String getMessageRefNo() {
        return messageRefNo;
    }

    public void setMessageRefNo(String messageRefNo) {
        this.messageRefNo = messageRefNo;
    }

    public String getLoginID() {
        return loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getExtTransactionCode() {
        return extTransactionCode;
    }

    public void setExtTransactionCode(String extTransactionCode) {
        this.extTransactionCode = extTransactionCode;
    }

    public String getDplName() {
        return dplName;
    }

    public void setDplName(String dplName) {
        this.dplName = dplName;
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

    public String getRecordLength() {
        return recordLength;
    }

    public void setRecordLength(String recordLength) {
        this.recordLength = recordLength;
    }

    public String getNoOfRecords() {
        return noOfRecords;
    }

    public void setNoOfRecords(String noOfRecords) {
        this.noOfRecords = noOfRecords;
    }

    public Integer getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Integer transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Integer getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Integer transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getDestQueueManager() {
        return destQueueManager;
    }

    public void setDestQueueManager(String destQueueManager) {
        this.destQueueManager = destQueueManager;
    }

    public String getDestQueue() {
        return destQueue;
    }

    public void setDestQueue(String destQueue) {
        this.destQueue = destQueue;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getSystemStatus() {
        return systemStatus;
    }

    public void setSystemStatus(String systemStatus) {
        this.systemStatus = systemStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getTerminalID() {
        return terminalID;
    }

    public void setTerminalID(String terminalID) {
        this.terminalID = terminalID;
    }

    public String getReplyRefNo() {
        return replyRefNo;
    }

    public void setReplyRefNo(String replyRefNo) {
        this.replyRefNo = replyRefNo;
    }

    public String getOverrideTellerId() {
        return overrideTellerId;
    }

    public void setOverrideTellerId(String overrideTellerId) {
        this.overrideTellerId = overrideTellerId;
    }

    @XmlElement(name = "OvdTellerID")
    private String overrideTellerId = null;

}
