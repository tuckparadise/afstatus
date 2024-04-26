package com.rhb.api.models;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;
import org.springframework.validation.annotation.Validated;

@Schema(description = "Flash Mapping Field <Header Response>")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-12-14T03:56:16.443398Z[GMT]")

public class CCFlashResHeader {
    @XmlElement(name = "MsgVerNo")
    private Double msgVerNo = null;

    @XmlElement(name = "SourceId")
    private String sourceId = null;

    @XmlElement(name = "MsgRefNo")
    private String msgRefNo = null;

    @XmlElement(name = "LoginId")
    private Integer loginId = null;

    @XmlElement(name = "Pan")
    private String pan = null;

    @XmlElement(name = "ExtTrxnCd")
    private String extTrxnCd = null;

    @XmlElement(name = "DplName")
    private String dplName = null;

    @XmlElement(name = "Filler")
    private String filler = null;

    @XmlElement(name = "Reclen")
    private Integer reclen = null;

    @XmlElement(name = "NoOfRec")
    private Integer noOfRec = null;

    @XmlElement(name = "TrxnDate")
    private Integer trxnDate = null;

    @XmlElement(name = "TrxnTime")
    private Integer trxnTime = null;

    @XmlElement(name = "DestQueueMgr")
    private String destQueueMgr = null;

    @XmlElement(name = "DestQueue")
    private String destQueue = null;

    @XmlElement(name = "ErrCode")
    private Integer errCode = null;

    @XmlElement(name = "SystemStatus")
    private String systemStatus = null;

    @XmlElement(name = "ErrMsg")
    private String errMsg = null;

    @XmlElement(name = "TerminalId")
    private String terminalId = null;

    @XmlElement(name = "RepRefNo")
    private String repRefNo = null;

    @XmlElement(name = "OvdTellerId")
    private Integer ovdTellerID = null;

    @XmlElement(name = "PriAcctCurrNo")
    private String priAcctCurrNo = null;

    @XmlElementWrapper(name = "RepliedMsg")
    @XmlElement(name = "Msg")
    private List<CCMessageList> msgList = null;

    public Double getMsgVerNo() {
        return msgVerNo;
    }

    public void setMsgVerNo(Double msgVerNo) {
        msgVerNo = msgVerNo;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getMsgRefNo() {
        return msgRefNo;
    }

    public void setMsgRefNo(String msgRefNo) {
        this.msgRefNo = msgRefNo;
    }


    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getExtTrxnCd() {
        return extTrxnCd;
    }

    public void setExtTrxnCd(String extTrxnCd) {
        this.extTrxnCd = extTrxnCd;
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

    public String getDestQueueMgr() {
        return destQueueMgr;
    }

    public void setDestQueueMgr(String destQueueMgr) {
        this.destQueueMgr = destQueueMgr;
    }

    public String getDestQueue() {
        return destQueue;
    }

    public void setDestQueue(String destQueue) {
        this.destQueue = destQueue;
    }


    public String getSystemStatus() {
        return systemStatus;
    }

    public void setSystemStatus(String systemStatus) {
        this.systemStatus = systemStatus;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getRepRefNo() {
        return repRefNo;
    }

    public void setRepRefNo(String repRefNo) {
        this.repRefNo = repRefNo;
    }

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    public Integer getReclen() {
        return reclen;
    }

    public void setReclen(Integer reclen) {
        this.reclen = reclen;
    }

    public Integer getNoOfRec() {
        return noOfRec;
    }

    public void setNoOfRec(Integer noOfRec) {
        this.noOfRec = noOfRec;
    }

    public Integer getTrxnDate() {
        return trxnDate;
    }

    public void setTrxnDate(Integer trxnDate) {
        this.trxnDate = trxnDate;
    }

    public Integer getTrxnTime() {
        return trxnTime;
    }

    public void setTrxnTime(Integer trxnTime) {
        this.trxnTime = trxnTime;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public Integer getOvdTellerID() {
        return ovdTellerID;
    }

    public void setOvdTellerID(Integer ovdTellerID) {
        this.ovdTellerID = ovdTellerID;
    }

    public String getPriAcctCurrNo() {
        return priAcctCurrNo;
    }

    public void setPriAcctCurrNo(String priAcctCurrNo) {
        this.priAcctCurrNo = priAcctCurrNo;
    }

    public List<CCMessageList> getMsgList() {
        return msgList;
    }

    public void setMsgList(List<CCMessageList> msgList) {
        this.msgList = msgList;
    }

}
