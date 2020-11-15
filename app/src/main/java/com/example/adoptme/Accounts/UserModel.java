package com.example.adoptme.Accounts;

import java.util.ArrayList;

/**
 * Model for a user who is seeking to adopt a pet
 */
public class UserModel {

    private String uid, name, phone,type;
    private int profilePic;


//    private ArrayList<String> typeFilters;
//    private ArrayList<Animal> likedAnimals;
//    /**
//     * ageFilter stores the range of ages a user wants, where ageFilter[0] = youngest age wanted, and ageFilter[1] = oldest age wanted.
//     */
//    private int[] ageFilter;

    public UserModel(String uid, String name, String phone, String type) {
        this.uid = uid;
        this.name = name;
        this.phone = phone;
        this.type = type;

    }

    public String getUid(){return uid;}

    public void setUid(String uid){this.uid = uid;}

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
