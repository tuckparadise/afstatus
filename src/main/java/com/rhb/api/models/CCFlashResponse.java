package com.rhb.api.models;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class CCFlashResponse {

    @XmlElement(name = "Header")
    private CCFlashResHeader header = null;

    @XmlElementWrapper(name = "Body")
    @XmlElement(name = "AppList")
    private List<CCFlashResBody> body = null;

    public CCFlashResHeader getHeader() {
        return header;
    }

    public void setHeader(CCFlashResHeader header) {
        this.header = header;
    }

    public List<CCFlashResBody> getBody() {
        return body;
    }

    public void setBody(List<CCFlashResBody> body) {
        this.body = body;
    }
}
