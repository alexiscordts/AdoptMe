package com.example.adoptme.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adoptme.Accounts.Animal;
import com.example.adoptme.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SavedPetsAdapter extends RecyclerView.Adapter<SavedPetsAdapter.ViewHolder> {

    private ArrayList<Animal> savedAnimals;

    public SavedPetsAdapter(ArrayList<Animal> savedAnimals) {
        this.savedAnimals = savedAnimals;
    }

    @NonNull
    @Override
    public SavedPetsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.liked_pets_card, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedPetsAdapter.ViewHolder holder, int position) {
            holder.setData(savedAnimals.get(position));
    }

    @Override
    public int getItemCount() {
        return savedAnimals.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView petImage;
        TextView name, age;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            petImage = itemView.findViewById(R.id.pet_image);
            name = itemView.findViewById(R.id.pet_name);
            age = itemView.findViewById(R.id.pet_age);
        }

        public void setData(Animal animal){



//            Picasso.get()
//                    .load(animal.getImage())
//                    .fit()
//                    .centerCrop()
//                    .into(petImage);

            name.setText(animal.getName());
            age.setText(animal.getAge() + "");
        }

        }


}


