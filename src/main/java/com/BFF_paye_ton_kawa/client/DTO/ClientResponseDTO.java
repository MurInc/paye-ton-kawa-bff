package com.BFF_paye_ton_kawa.client.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientResponseDTO {
    private String email;
    private String first_name;
    private String last_name;
    private String birth_date;
    private String is_validated;
    private String auth_id;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getIs_validated() {
        return is_validated;
    }

    public void setIs_validated(String is_validated) {
        this.is_validated = is_validated;
    }

    public String getAuth_id() {
        return auth_id;
    }

    public void setAuth_id(String auth_id) {
        this.auth_id = auth_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    private String created_at;




    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public String getAge() {
//        return age;
//    }
//
//    public void setAge(String age) {
//        this.age = age;
//    }
}
