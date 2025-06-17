package com.bff_paye_ton_kawa.Utils;

public class DataContent<T> {
    private String type;
    private String id;
    private T attributes;

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public T getAttributes() {
        return attributes;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAttributes(T attributes) {
        this.attributes = attributes;
    }
}
