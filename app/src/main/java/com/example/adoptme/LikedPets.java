package com.example.adoptme;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adoptme.Accounts.Adopter;
import com.example.adoptme.Accounts.Animal;
import com.example.adoptme.Accounts.AnimalShelter;
import com.example.adoptme.Accounts.UserModel;
import com.example.adoptme.Adapters.SavedPetsAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LikedPets extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Adopter currentUser;
    private ArrayList<Animal> likedAnimals;

    private DatabaseReference databaseReference;
    private StorageReference mStorageRef;

    private FirebaseRecyclerAdapter<Animal, SavedPetsAdapter.ViewHolder> firebaseRecyclerAdapter;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked_pets);

        auth = FirebaseAuth.getInstance();
        String uid = auth.getUid();

        mStorageRef = FirebaseStorage.getInstance().getReference();



        recyclerView = findViewById(R.id.savedPetsRecyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        databaseReference = FirebaseDatabase.getInstance().getReference();
        Query query = databaseReference.child("users").child(uid).child("likedAnimals");

        FirebaseRecyclerOptions<Animal> firebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<Animal>()
                .setQuery(query, Animal.class)
                .build();

        //Get current user or if no user create a new one...


        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Animal, SavedPetsAdapter.ViewHolder>(firebaseRecyclerOptions) {
            @Override
            protected void onBindViewHolder(@NonNull SavedPetsAdapter.ViewHolder holder, int position, @NonNull Animal model) {
                holder.setData(model);
            }

            @NonNull
            @Override
            public SavedPetsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.liked_pets_card,parent,false);
                return new SavedPetsAdapter.ViewHolder(view);
            }
        };



        recyclerView.setAdapter(firebaseRecyclerAdapter);


    }
    @Override
    protected void onStart() {
        super.onStart();
        firebaseRecyclerAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (firebaseRecyclerAdapter!= null) {
            firebaseRecyclerAdapter.stopListening();
        }
    }

    public ArrayList<Animal> addTempLikedList(){
        ArrayList<Animal> animals = new ArrayList<>();
//        animals.add(new Animal("Eevee",R.drawable.eevee, 8, "Dog"));
//        animals.add(new Animal("Iron",R.drawable.iron, 3, "Dog"));

        return animals;
    }




}
