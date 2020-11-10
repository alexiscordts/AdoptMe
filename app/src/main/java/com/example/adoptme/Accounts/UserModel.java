package com.example.adoptme.Accounts;

import java.util.ArrayList;

/**
 * Model for a user who is seeking to adopt a pet
 */
public class UserModel {

    private String email, name, phone;
    private int profilePic;
    private ArrayList<String> typeFilters;
    private ArrayList<Animal> likedAnimals;
    /**
     * ageFilter stores the range of ages a user wants, where ageFilter[0] = youngest age wanted, and ageFilter[1] = oldest age wanted.
     */
    private int[] ageFilter;

    public UserModel(String email, String name, String phone, int profilePic, ArrayList<Animal> likedAnimals) {
        typeFilters = new ArrayList<>();
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.profilePic = profilePic;
        this.likedAnimals = likedAnimals;

        ageFilter = new int[]{0, 100};
    }

    /**
     * Modify the age filter of a user. Assume that youngest <= oldest.
     * @param youngest the youngest age wanted for a pet
     * @param oldest the oldest age wanted for a pet
     */
    public void changeAgeFilter(int youngest, int oldest){
        ageFilter[0] = youngest;
        ageFilter[1] = oldest;
    }

    public int[] getAgeFilter(){
        return ageFilter;
    }

    public void addTypeFilter(String type){
        typeFilters.add(type);
    }
    public ArrayList<String> getTypeFilters(){
        return typeFilters;
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

    public void addLikedAnimal(Animal animal){
        likedAnimals.add(animal);
    }
    public ArrayList<Animal> getLikedAnimals(){
        return likedAnimals;
    }

}
