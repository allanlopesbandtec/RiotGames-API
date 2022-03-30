package com.riotgames.api.model;

import javax.xml.bind.annotation.XmlElement;

public class ErrorXmlApi {

    @XmlElement(namespace = "Code")
    public String code;
    @XmlElement(namespace = "Message")
    public String message;
    @XmlElement(namespace = "RequestId")
    public String requestId;
    @XmlElement(namespace = "HostId")
    public String hostId;

    public ErrorXmlApi() {
    }

    public ErrorXmlApi(String code, String message, String requestId, String hostId) {
        this.code = code;
        this.message = message;
        this.requestId = requestId;
        this.hostId = hostId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }
}
