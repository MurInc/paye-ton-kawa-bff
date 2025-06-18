package com.BFF_paye_ton_kawa.exceptions.schema;

import java.util.List;

public class ErrorList {
    private List<ApiError> errors;

    public List<ApiError> getErrors() {
        return errors;
    }

    public void setErrors(List<ApiError> errors) {
        this.errors = errors;
    }
}
