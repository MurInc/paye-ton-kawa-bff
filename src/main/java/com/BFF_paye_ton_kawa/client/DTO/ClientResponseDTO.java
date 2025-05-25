package com.BFF_paye_ton_kawa.client.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientResponseDTO {
    @JsonProperty("_id")
    public String id;
//    public String userName;
    public String email;
//    public String age;

//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
