package com.example.adoptme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adoptme.Accounts.Animal;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CardStackAdapter extends RecyclerView.Adapter<CardStackAdapter.ViewHolder> {

    private ArrayList<Animal> animals;

    public CardStackAdapter(ArrayList<Animal> animals) {
        this.animals = animals;
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
