package com.example.adoptme.Accounts;

import java.util.ArrayList;

/**
 * Model for a user who is seeking to adopt a pet
 */
public class UserModel {

    private String email, name, phone,type;
    private int profilePic;


//    private ArrayList<String> typeFilters;
//    private ArrayList<Animal> likedAnimals;
//    /**
//     * ageFilter stores the range of ages a user wants, where ageFilter[0] = youngest age wanted, and ageFilter[1] = oldest age wanted.
//     */
//    private int[] ageFilter;

    public UserModel(String email, String name, String phone, int profilePic, String type) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.profilePic = profilePic;
        this.type = type;

    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(int profilePic) {
        this.profilePic = profilePic;
    }


}
