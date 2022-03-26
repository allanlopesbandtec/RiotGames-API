package com.riotgames.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

public class ApiError extends Exception {

    private Class clazz;

    private String description;

    private HttpStatus httpStatus;

    private String bodyErrorRequest;

    public ApiError(Class clazz, String description, HttpStatus httpStatus, String bodyErrorRequest) {
        this.clazz = clazz;
        this.description = description;
        this.httpStatus = httpStatus;
        this.bodyErrorRequest = bodyErrorRequest;
    }

    public ApiError(Class clazz, String description) {
        this.clazz = clazz;
        this.description = description;
    }

    public ApiError(Class clazz, String description, String bodyErrorRequest) {
        this.clazz = clazz;
        this.description = description;
        this.bodyErrorRequest = bodyErrorRequest;
    }

    //Retira throwable
    @JsonIgnore
    @Override
    public synchronized Throwable getCause() {
        return super.getCause();
    }

    //Retira StackTraceElement[]
    @JsonIgnore
    @Override
    public StackTraceElement[] getStackTrace() {
        return super.getStackTrace();
    }

    //Retira Message
    @JsonIgnore
    @Override
    public String getMessage() {
        return super.getMessage();
    }

    //Retira localizedMessage
    @JsonIgnore
    @Override
    public String getLocalizedMessage() {
        return super.getLocalizedMessage();
    }

    @JsonIgnore
    @Override
    public synchronized Throwable fillInStackTrace() {
        return super.fillInStackTrace();
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getBodyErrorRequest() {
        return bodyErrorRequest;
    }

    public void setBodyErrorRequest(String bodyErrorRequest) {
        this.bodyErrorRequest = bodyErrorRequest;
    }
}
