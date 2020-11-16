package com.example.adoptme.Accounts;

import java.lang.reflect.Array;
import java.security.acl.AclNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Adopter extends UserModel{


    private ArrayList<String> typeFilters;
    private ArrayList<Animal> likedAnimals;
    private ArrayList<Integer> ageFilter;

    public Adopter(){
        // default constructor
    }

    public Adopter(String name, String phone, ArrayList<Animal> likedAnimals) {
        super(name, phone, "adopter");
        this.likedAnimals = likedAnimals;

        this.typeFilters = new ArrayList<>();
        this.ageFilter = new ArrayList<Integer>();
        this.setType("adopter");


        ageFilter.add(0,0);
        ageFilter.add(1, 100);
    }



    public void setTypeFilters(ArrayList<String> typeFilters) {
        this.typeFilters = typeFilters;
    }

    public void setLikedAnimals(ArrayList<Animal> likedAnimals) {
        this.likedAnimals = likedAnimals;
    }

    public void setAgeFilter(ArrayList<Integer> ageFilter) {
        this.ageFilter = ageFilter;
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
        if(likedAnimals == null){
            likedAnimals = new ArrayList<Animal>();
        }
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
