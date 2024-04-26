package com.rhb.api.models;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.validation.annotation.Validated;

@Schema(description = "FLASH Mapping Field <Request>")
@Validated
@XmlRootElement(name = "Message")
@XmlAccessorType(XmlAccessType.FIELD)
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-12-14T03:56:16.443398Z[GMT]")
public class EAIRequest {
    public EAIRequestHeader getHeader() {
        return header;
    }

    public void setHeader(EAIRequestHeader header) {
        this.header = header;
    }

    @XmlElement(name = "Header")
    EAIRequestHeader header;

    @XmlElement(name = "Body")
    EAIRequestBody body;

    public EAIRequestBody getBody() {
        return body;
    }

    public void setBody(EAIRequestBody body) {
        this.body = body;
    }
}
