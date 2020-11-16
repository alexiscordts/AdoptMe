package com.example.adoptme.Accounts;

import java.util.ArrayList;

public class AnimalShelter extends UserModel {

    private String phoneNum, address, accountType;

    private ArrayList<Animal> animals;

    public AnimalShelter(){

    }

    public AnimalShelter(String name, String phone, String address, ArrayList<Animal> animals) {
        super(name, phone, "shelter");
        this.address = address;
        setType("shelter");
        this.animals = animals;
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setAnimals(ArrayList<Animal> animals) {
        this.animals = animals;
    }

    public void addAnimal(String name, int image, int age, String type){
        animals.add(new Animal(name, image, age, type));
    }

}
