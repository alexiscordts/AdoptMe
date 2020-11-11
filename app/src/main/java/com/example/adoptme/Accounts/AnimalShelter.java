package com.example.adoptme.Accounts;

import java.util.ArrayList;

public class AnimalShelter extends UserModel {

    String address;
    private ArrayList<Animal> animals;

    public AnimalShelter(String email, String name, String phone, int profilePic, String address, ArrayList<Animal> animals) {
        super(email, name, phone, profilePic);
        this.address = address;
        setType("shelter");
        this.animals = animals;
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public void addAnimal(String name, int image, int age, String type){
        animals.add(new Animal(name, image, age, type));
    }

}
