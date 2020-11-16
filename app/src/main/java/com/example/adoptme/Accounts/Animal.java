package com.example.adoptme.Accounts;

public class Animal {
    public Animal() {
    }

    int  age;
    String name,type, notes, img;
    AnimalShelter shelter;

    public Animal(String name,String image, int age, String type){
        this.img = image;
        this.name = name;
        this.age= age;
        this.type = type;

    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getAge() {
        return age;
    }

    public String getImage(){
        return img;
    }
}
