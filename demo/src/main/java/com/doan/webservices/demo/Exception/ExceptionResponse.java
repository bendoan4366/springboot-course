package com.doan.webservices.demo.Exception;

import java.sql.Timestamp;
import java.util.Date;

public class ExceptionResponse {
    //timestamp
    //message
    //detail

    private Timestamp errorTimestamp;
    private String errorMessage;
    private String errorDetails;

    public ExceptionResponse(Timestamp timestamp, String errorMessage, String errorDetails) {
        super();
        this.errorTimestamp = timestamp;
        this.errorMessage = errorMessage;
        this.errorDetails = errorDetails;
    }

    public Timestamp getErrorTimestamp() {
        return errorTimestamp;
    }

    public void setErrorTimestamp(Timestamp errorTimestamp) {
        this.errorTimestamp = errorTimestamp;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(String errorDetails) {
        this.errorDetails = errorDetails;
    }
}
