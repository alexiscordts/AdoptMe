package com.example.adoptme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.adoptme.Accounts.Adopter;
import com.example.adoptme.Accounts.Animal;
import com.example.adoptme.Accounts.AnimalShelter;
import com.example.adoptme.Adapters.SavedPetsAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

public class ShelterView extends AppCompatActivity implements TextWatcher {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private AnimalShelter animalShelter;
    private View dialogView;
    private ArrayList<Animal> mAnimals;
    FloatingActionButton fab;
    TextView tvShelterName, tvShelterPhone, tvShelterEmail;
    ImageView addNewAnimalImg;

    EditText mNameEdit, mAgeEdit, mTypeEdit;
    private boolean mEntryValid;

    private Uri imageUri;

    //Firebase storage
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_view);

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        tvShelterEmail = findViewById(R.id.tvShelterEmail);
        tvShelterName = findViewById(R.id.tvShelterName);
        tvShelterPhone = findViewById(R.id.tvShelterPhone);

        recyclerView = findViewById(R.id.availablePetView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        //Get current user or if no user create a new one...
        //TODO : Get animal array from database


        animalShelter = new AnimalShelter("shelter@", "arl", "525","", addList());
        mAnimals = animalShelter.getAnimals();

        adapter = new SavedPetsAdapter(mAnimals);

        recyclerView.setAdapter(adapter);

        tvShelterName.setText(animalShelter.getName());
        tvShelterPhone.setText(animalShelter.getPhone());

        fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddAnimalDialog(-1);
            }
        });


    }


    private void showAddAnimalDialog(final int addAnimalPosition){
         dialogView = LayoutInflater.from(this)
                .inflate(R.layout.add_animal, null);


        mNameEdit = dialogView.findViewById(R.id.animalName);
        mAgeEdit = dialogView.findViewById(R.id.animalAge);
        mTypeEdit = dialogView.findViewById(R.id.addAnimalType);
        addNewAnimalImg = dialogView.findViewById(R.id.addAnimalImage);

        addNewAnimalImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(takePictureIntent.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });



        //Listen to text changes

        mNameEdit.addTextChangedListener(this);
        mAgeEdit.addTextChangedListener(this);
        mTypeEdit.addTextChangedListener(this);

        final boolean editing = addAnimalPosition > -1;

        String dialogTitle = editing ? getString(R.string.edit_animal) :
                getString(R.string.new_animal);

        // Builds the AlertDialog and sets the custom view. Pass null for
        // the positive and negative buttons, as you will override the button
        // presses manually to perform validation before closing the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setView(dialogView)
                .setTitle(dialogTitle)
                .setPositiveButton(R.string.save, null)
                .setNegativeButton(R.string.cancel, null);

        final AlertDialog dialog = builder.show();


//        if (editing) {
//           Animal editAnimal = mAnimals.get(addAnimalPosition);
//            mNameEdit.setText(editAnimal.getName());
//            mNameEdit.setEnabled(false);
//            mAgeEdit.setText(editAnimal.getAge());
//            mAgeEdit.setEnabled(false);
//            mTypeEdit.setText(editAnimal.getType());
//        }

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mEntryValid){
                    Animal newAnimal = new Animal(mNameEdit.getText().toString(), "", Integer.parseInt(mAgeEdit.getText().toString()), mTypeEdit.getText().toString().toLowerCase());

                    mAnimals.add(newAnimal);
                    adapter.notifyDataSetChanged();



                    //TODO : Push new animal Data to Firebase

                    dialog.dismiss();


                }else{
                    Toast.makeText(ShelterView.this, "Invalid entry", Toast.LENGTH_SHORT).show();

                }

            }
        });






    }

    public ArrayList<Animal> addList(){
        ArrayList<Animal> animals = new ArrayList<>();
//        animals.add(new Animal("Eevee",R.drawable.eevee, 8, "Dog"));
//        animals.add(new Animal("Iron",R.drawable.iron, 3, "Dog"));

        return animals;
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        boolean nameValid = !mNameEdit.getText().toString().isEmpty();
        boolean ageValid = !mAgeEdit.getText().toString().isEmpty();
        boolean typeValid = !mTypeEdit.getText().toString().isEmpty();

        mEntryValid = nameValid & ageValid & typeValid;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ((ImageView) dialogView.findViewById(R.id.addAnimalImage)).setImageBitmap(imageBitmap);

            imageUri = data.getData();
            uploadPicture();




        }
    }

    private void uploadPicture() {

        final String randomKey = UUID.randomUUID().toString();

        StorageReference riversRef = storageReference.child("images/" + randomKey);

        riversRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        Toast.makeText(ShelterView.this, "Added to storage", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });
    }

    public ArrayList<Animal> retrieveAnimals(){
        ArrayList<Animal> animals = new ArrayList<>();





        return animals;

    }


}