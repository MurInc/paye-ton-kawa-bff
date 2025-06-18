package com.BFF_paye_ton_kawa.exceptions.schema;


import org.springframework.http.HttpStatusCode;

public class ApiError {
    private HttpStatusCode status;
    private String message;

    public ApiError() {}

    public ApiError(HttpStatusCode status, String message) {
        this.status = status;
        this.message = message;
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
}
