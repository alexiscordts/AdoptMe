package com.example.adoptme.Accounts;

import java.util.ArrayList;

public class Adopter {

    private String uid, name, animalType, animalAge, accountType;
    private ArrayList<Animal> likedAnimals, dislikedAnimals;

    public Adopter(String uid, String name, String accountType){
        this.uid = uid;
        this.name = name;
        this.accountType = accountType;

        this.animalType = null;
        this.animalAge = null;
        this.likedAnimals = null;
        this.dislikedAnimals = null;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public String getAnimalAge() {
        return animalAge;
    }

    public void setAnimalAge(String animalAge) {
        this.animalAge = animalAge;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public ArrayList<Animal> getLikedAnimals() {
        return likedAnimals;
    }

    public void setLikedAnimals(ArrayList<Animal> likedAnimals) {
        this.likedAnimals = likedAnimals;
    }

    public ArrayList<Animal> getDislikedAnimals() {
        return dislikedAnimals;
    }

    public void setDislikedAnimals(ArrayList<Animal> dislikedAnimals) {
        this.dislikedAnimals = dislikedAnimals;
    }
}
