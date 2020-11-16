package com.example.adoptme.Accounts;

import java.util.ArrayList;

/**
 * Model for a user who is seeking to adopt a pet
 */
public class UserModel {

    private String name, phone,type;
    private int profilePic;

    public UserModel(){

    }

    public UserModel(String name, String phone, String type) {
        this.name = name;
        this.phone = phone;
        this.type = type;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
