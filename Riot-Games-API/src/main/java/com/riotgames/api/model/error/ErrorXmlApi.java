package com.riotgames.api.model.error;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "Error")
public class ErrorXmlApi {

    public String Code;

    public String Message;

    public String RequestId;

    public String HostId;

    @JsonIgnore
    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    @JsonIgnore
    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    @JsonIgnore
    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String requestId) {
        RequestId = requestId;
    }

    @JsonIgnore
    public String getHostId() {
        return HostId;
    }

    public void setHostId(String hostId) {
        HostId = hostId;
    }
}
