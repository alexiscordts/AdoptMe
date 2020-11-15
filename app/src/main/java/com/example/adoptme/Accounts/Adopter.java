package com.example.adoptme.Accounts;

import java.util.ArrayList;

public class Adopter extends UserModel{


    private ArrayList<String> typeFilters;
    private ArrayList<Animal> likedAnimals;
    /**
     * ageFilter stores the range of ages a user wants, where ageFilter[0] = youngest age wanted, and ageFilter[1] = oldest age wanted.
     */
    private int[] ageFilter;

    public Adopter(String email, String name, String phone, int profilePic, ArrayList<Animal> likedAnimals) {
        super(email, name, phone, profilePic, "adopter");
        typeFilters = new ArrayList<>();
        this.likedAnimals = likedAnimals;
        ageFilter = new int[]{0, 100};
        this.setType("adopter");
    }


    /**
     * Modify the age filter of a user. Assume that youngest <= oldest.
     * @param youngest the youngest age wanted for a pet
     */
    public void changeMinAge(int youngest){
        ageFilter[0] = youngest;
    }
    public void changeMaxAge(int oldest){
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

    public void addLikedAnimal(Animal animal){
        likedAnimals.add(animal);
    }
    public ArrayList<Animal> getLikedAnimals(){
        return likedAnimals;
    }




}
