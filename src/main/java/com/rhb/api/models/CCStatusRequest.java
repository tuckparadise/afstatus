package com.rhb.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.Valid;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.validation.annotation.Validated;

@Schema(description = "FLASH Mapping Field <Request>")
@Validated
@XmlRootElement(name = "Message")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-12-14T03:56:16.443398Z[GMT]")
public class CCStatusRequest {

    @JsonProperty("header")
    private CCStatusReqHeader header = null;

    @JsonProperty("body")
    private CCReqBody body = null;

    public CCReqBody getBody() {
        return body;
    }

    public void setBody(CCReqBody body) {
        this.body = body;
    }

    @Schema(required = true, description = "header")
    @Valid
    public CCStatusReqHeader getHeader() {
        return header;
    }

    public void setHeader(CCStatusReqHeader header) {
        this.header = header;
    }
}
