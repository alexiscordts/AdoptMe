package com.example.adoptme.Accounts;

import java.util.ArrayList;

public class AnimalShelter {

    private String name;
    private ArrayList<Animal> animals;

    public AnimalShelter(String name, ArrayList<Animal> animals){
        this.name = name;
        this.animals = animals;
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public void addAnimal(String name, int image, int age, String type){
        animals.add(new Animal(name, image, age, type));
    }

}
