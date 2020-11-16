package com.example.adoptme.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adoptme.Accounts.Adopter;
import com.example.adoptme.Accounts.Animal;
import com.example.adoptme.Accounts.UserModel;
import com.example.adoptme.R;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CardStackAdapter extends RecyclerView.Adapter<CardStackAdapter.ViewHolder> {

    private ArrayList<Animal> animals;
    private ArrayList<Integer> ageFilter;
    private ArrayList<String> typeFilter;

    public CardStackAdapter(ArrayList<Animal> animals, Adopter currentUser) {
        this.ageFilter = currentUser.getAgeFilter();
        this.typeFilter = currentUser.getTypeFilters();
        this.animals = getfilteredAnimals(animals);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(animals.get(position));
    }

    @Override
    public int getItemCount() {
        return animals.size();
    }

    public ArrayList<Animal> getAnimals(){
        return animals;
    }

    public ArrayList<Animal> getfilteredAnimals(ArrayList<Animal> animals){

        ArrayList<Animal> filteredAnimals = new ArrayList<>();

        if(typeFilter == null || typeFilter.size() == 0){
            //There is no type filter, but there is always an age filter.
            int startAge = ageFilter.get(0);
            int endAge = ageFilter.get(1);

            for(int i=0; i< animals.size(); i++) {
                Animal currentAnimal = animals.get(i);
                int tempAge = currentAnimal.getAge();

                if((tempAge > startAge && tempAge < endAge) || tempAge == startAge || tempAge == endAge) {
                    filteredAnimals.add(currentAnimal);
                }
            }

        }else{
           //we have a type filter and age filter

            //age and type filter
            int startAge = ageFilter.indexOf(0);
            int endAge = ageFilter.indexOf(1);

            for(int i =0; i < typeFilter.size(); i++){

                String currentType = typeFilter.get(i);

                for(int j=0; j< animals.size(); j++){
                    Animal currentAnimal = animals.get(j);
                    if(currentAnimal.getType().toLowerCase().trim().equals(currentType.toLowerCase())){
                        int tempAge = currentAnimal.getAge();
                        if((tempAge > startAge && tempAge < endAge) || tempAge == startAge || tempAge == endAge) {
                            filteredAnimals.add(currentAnimal);
                        }
                    }
                }
            }

        }


        return filteredAnimals;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView petImage;
        TextView name, age;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            petImage = itemView.findViewById(R.id.pet_image);
            name = itemView.findViewById(R.id.pet_name);
            age = itemView.findViewById(R.id.pet_age);
        }

        public void setData(Animal animal){

            Picasso.get()
                    .load(animal.getImage())
                    .fit()
                    .centerCrop()
                    .into(petImage);

            name.setText(animal.getName());
            age.setText(animal.getAge() + "");
        }
    }
}
