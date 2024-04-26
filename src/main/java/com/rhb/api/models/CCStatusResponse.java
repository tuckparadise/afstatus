package com.rhb.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import org.springframework.validation.annotation.Validated;

@Schema(description = "FLASH Mapping Field <Response>")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-12-14T03:56:16.443398Z[GMT]")
public class CCStatusResponse {
    @JsonProperty("header")
    private List<CCStatusResHeader> header = null;

    public void setHeader(List<CCStatusResHeader> header) {
        this.header = header;
    }

    /*@JsonProperty("eaiResponse")
    private List<PfFlashResponseHeader> responseHeaders = null;

    public List<PfFlashResponseHeader> getResponseHeaders() {
        return responseHeaders;
    }

    public void setResponseHeaders(List<PfFlashResponseHeader> responseHeaders) {
        this.responseHeaders = responseHeaders;
    }
*/

    public List<CCStatusResBody> getBody() {
        return body;
    }

    public void setBody(List<CCStatusResBody> body) {
        this.body = body;
    }

    @JsonProperty("body")
    private List<CCStatusResBody> body = null;

    public List<CCStatusResHeader> getHeader() {
        return header;
    }
}
