package com.example.adoptme.Accounts;

import android.view.animation.AnimationUtils;

public class Animal {

    private int image, age;
    private String name,type;

    public Animal(){

    }

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

    public void setImage(int image) {
        this.image = image;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }
}
