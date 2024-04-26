package com.rhb.api.models;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.validation.annotation.Validated;

/**
 * Description of CCMessageList
 */
@Schema(description = "Description of CCMessageList")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-12-20T05:42:18.865906Z[GMT]")

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Msg")
public class CCMessageList {
    @XmlElement(name = "MsgSev")
    private String messageSeverity = null;

    @XmlElement(name = "MsgDscp")
    private String messageDesc = null;

    @XmlElement(name = "MsgCd")
    private String messageCode = null;

    @XmlElement(name = "ProgId")
    private String progID = null;

    public CCMessageList messageSeverity(String messageSeverity) {
        this.messageSeverity = messageSeverity;
        return this;
    }

    /**
     * Message Severity
     * @return messageSeverity
     **/
    @Schema(required = true, description = "Message Severity")
    @NotNull

    public String getMessageSeverity() {
        return messageSeverity;
    }

    public void setMessageSeverity(String messageSeverity) {
        this.messageSeverity = messageSeverity;
    }

    public CCMessageList messageDesc(String messageDesc) {
        this.messageDesc = messageDesc;
        return this;
    }

    /**
     * Message Description
     * @return messageDesc
     **/
    @Schema(example = "PROCESSING COMPLETE", required = true, description = "Message Description")
    @NotNull

    public String getMessageDesc() {
        return messageDesc;
    }

    public void setMessageDesc(String messageDesc) {
        this.messageDesc = messageDesc;
    }

    public CCMessageList messageCode(String messageCode) {
        this.messageCode = messageCode;
        return this;
    }

    /**
     * Message Code
     * @return messageCode
     **/
    @Schema(required = true, description = "Message Code")
    @NotNull

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public CCMessageList progID(String progID) {
        this.progID = progID;
        return this;
    }

    /**
     * Prog ID
     * @return progID
     **/
    @Schema(required = true, description = "Prog ID")
    @NotNull

    public String getProgID() {
        return progID;
    }

    public void setProgID(String progID) {
        this.progID = progID;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CCMessageList CCMessageList = (CCMessageList) o;
        return Objects.equals(this.messageSeverity, CCMessageList.messageSeverity) &&
                Objects.equals(this.messageDesc, CCMessageList.messageDesc) &&
                Objects.equals(this.messageCode, CCMessageList.messageCode) &&
                Objects.equals(this.progID, CCMessageList.progID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageSeverity, messageDesc, messageCode, progID);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CCMessageList {\n");

        sb.append("    messageSeverity: ").append(toIndentedString(messageSeverity)).append("\n");
        sb.append("    messageDesc: ").append(toIndentedString(messageDesc)).append("\n");
        sb.append("    messageCode: ").append(toIndentedString(messageCode)).append("\n");
        sb.append("    progID: ").append(toIndentedString(progID)).append("\n");
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