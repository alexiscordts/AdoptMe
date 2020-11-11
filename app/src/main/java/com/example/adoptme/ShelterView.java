package com.example.adoptme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adoptme.Accounts.Adopter;
import com.example.adoptme.Accounts.Animal;
import com.example.adoptme.Accounts.AnimalShelter;
import com.example.adoptme.Adapters.SavedPetsAdapter;

import java.util.ArrayList;

public class ShelterView extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private AnimalShelter animalShelter;

    Button addAnimalBtn;
    ImageView ivShelterImage;
    ImageButton ivSettingsBtn;
    TextView tvShelterName, tvShelterPhone, tvShelterEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_view);
        addAnimalBtn = findViewById(R.id.addAnimalBtn);
        ivShelterImage = findViewById(R.id.imgShelterProfile);
        ivSettingsBtn = findViewById(R.id.editShelterProfileBtn);
        tvShelterEmail = findViewById(R.id.tvShelterEmail);
        tvShelterName = findViewById(R.id.tvShelterName);
        tvShelterPhone = findViewById(R.id.tvShelterPhone);

        recyclerView = findViewById(R.id.savedPetsRecyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        //Get current user or if no user create a new one...

        animalShelter = new AnimalShelter("shelter@", "arl", "525",2,"234",addList());

        adapter = new SavedPetsAdapter(animalShelter.getAnimals());

        recyclerView.setAdapter(adapter);

        tvShelterEmail.setText(animalShelter.getEmail());
        tvShelterName.setText(animalShelter.getName());
        tvShelterPhone.setText(animalShelter.getPhone());


    }

    public ArrayList<Animal> addList(){
        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Eevee",R.drawable.eevee, 8, "Dog"));
        animals.add(new Animal("Iron",R.drawable.iron, 3, "Dog"));

        return animals;
    }


}