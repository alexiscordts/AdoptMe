package com.example.adoptme.Accounts;

public class Animal {

    int image;
    String name,type, age;

    public Animal(String name,int image, String age, String type){
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

    public String getAge() {
        return age;
    }
    public int getImage(){
        return image;
    }
}
