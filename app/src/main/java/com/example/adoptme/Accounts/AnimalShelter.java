package com.example.adoptme.Accounts;

import java.util.ArrayList;

public class AnimalShelter extends UserModel {

    private String uid, name, phoneNum, address, accountType;

    private ArrayList<Animal> animals;

    public AnimalShelter(String uid, String name, String phone, String address, ArrayList<Animal> animals) {
        super(uid, name, phone, "shelter");
        this.address = address;
        setType("shelter");
        this.animals = animals;
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public void addAnimal(String name, String image, int age, String type){
        animals.add(new Animal(name, image, age, type));
    }

}
