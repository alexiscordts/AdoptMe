package com.example.adoptme.Accounts;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Adopter extends UserModel{


    private ArrayList<String> typeFilters;
    private ArrayList<Animal> likedAnimals;
    /**
     * ageFilter stores the range of ages a user wants, where ageFilter[0] = youngest age wanted, and ageFilter[1] = oldest age wanted.
     */
    private ArrayList<Integer> ageFilter;

    public Adopter(String uid, String name, String phone, ArrayList<Animal> likedAnimals) {
        super(uid, name, phone, "adopter");
        typeFilters = new ArrayList<>();
        this.likedAnimals = likedAnimals;
        ageFilter = new ArrayList<Integer>();
        ageFilter.add(0,0);
        ageFilter.add(1, 100);
        this.setType("adopter");
    }


    /**
     * Modify the age filter of a user. Assume that youngest <= oldest.
     * @param youngest the youngest age wanted for a pet
     */
    public void changeAgeFilter(int youngest, int oldest){
        ageFilter.add(0, youngest);
        ageFilter.add(1, oldest);

    }

    public ArrayList<Integer> getAgeFilter(){
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

    public void changeMinAge(int progress) {
        ageFilter.set(0,progress);
    }
    public void changeMaxAge(int progress) {
        ageFilter.set(1,progress);
    }
}
