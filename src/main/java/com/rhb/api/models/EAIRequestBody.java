package com.rhb.api.models;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.validation.annotation.Validated;

@Schema(description = "FLASH Mapping Field <Request>")
@Validated
@XmlRootElement(name = "Body")
@XmlAccessorType(XmlAccessType.FIELD)
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-12-14T03:56:16.443398Z[GMT]")
public class EAIRequestBody {
    @XmlElement(name = "PrimaryID")
    private String primaryIDNo = null;

    @XmlElement(name = "ProductCode")
    private String productCode = null;

    @XmlElement(name="PartnerEmail")
    private String partnerEmail = null;

    public String getPartnerEmail() {
        return partnerEmail;
    }

    public void setPartnerEmail(String partnerEmail) {
        this.partnerEmail = partnerEmail;
    }

    @XmlElement(name = "FromDate")
    private String fromDate = null;

    @XmlElement(name = "ToDate")
    private String toDate = null;

    @XmlElement(name = "ExternalRefID")
    private String extRefID = null;

    @XmlElement(name = "SourceSystemCode")
    private String sourceSystemCode = null;

    public String getPrimaryIDNo() {
        return primaryIDNo;
    }

    public void setPrimaryIDNo(String primaryIDNo) {
        this.primaryIDNo = primaryIDNo;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getExtRefID() {
        return extRefID;
    }

    public void setExtRefID(String extRefID) {
        this.extRefID = extRefID;
    }

    public String getSourceSystemCode() {
        return sourceSystemCode;
    }

    public void setSourceSystemCode(String sourceSystemCode) {
        this.sourceSystemCode = sourceSystemCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlElement(name = "Username")
    private String username = null;


    @XmlElement(name = "Password")
    private String password = null;

}
