package com.example.adoptme;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adoptme.Accounts.Adopter;
import com.example.adoptme.Accounts.Animal;
import com.example.adoptme.Accounts.AnimalShelter;
import com.example.adoptme.Accounts.UserModel;
import com.example.adoptme.Adapters.SavedPetsAdapter;

import java.util.ArrayList;

public class LikedPets extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Adopter currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked_pets);

        recyclerView = findViewById(R.id.savedPetsRecyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        //Get current user or if no user create a new one...

        currentUser = new Adopter("vtorres@iastate.edu", "Veronica", "515-402-7893", addTempLikedList());




            adapter = new SavedPetsAdapter((ArrayList<Animal>) currentUser.getLikedAnimals());








        recyclerView.setAdapter(adapter);


    }

    public ArrayList<Animal> addTempLikedList(){
        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Eevee",R.drawable.eevee, 8, "Dog"));
        animals.add(new Animal("Iron",R.drawable.iron, 3, "Dog"));

        return animals;
    }




}
