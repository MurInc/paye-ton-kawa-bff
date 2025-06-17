package com.BFF_paye_ton_kawa.exceptions.schema;

import java.util.List;

public class ErrorList {
    private List<Error> errors;

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }
}
