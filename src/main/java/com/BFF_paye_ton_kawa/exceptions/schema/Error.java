package com.BFF_paye_ton_kawa.exceptions.schema;

import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

public class Error {
    private HttpStatusCode code;
    private HttpStatusCode status;
    private String title;
    private String detail;
    private LocalDateTime timestamp;
    private String message;

    public Error() {
        this.timestamp = LocalDateTime.now();
    }

    public HttpStatusCode getStatus() {
        return status;
    }

    public void setStatus(HttpStatusCode status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
