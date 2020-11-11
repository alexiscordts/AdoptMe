package com.example.adoptme.Accounts;

public class Animal {

    int image, age;
    String name,type;
    AnimalShelter shelter;

    public Animal(String name,int image, int age, String type){
        this.image = image;
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
    public int getImage(){
        return image;
    }
}
