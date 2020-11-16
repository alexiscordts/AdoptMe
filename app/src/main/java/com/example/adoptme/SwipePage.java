package com.example.adoptme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.Toast;

import com.example.adoptme.Accounts.Adopter;
import com.example.adoptme.Accounts.Animal;
import com.example.adoptme.Adapters.CardStackAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeableMethod;

import java.util.ArrayList;

public class SwipePage extends AppCompatActivity {

    private CardStackView cardStackView;
    private CardStackAdapter cardStackAdapter;
    private CardStackLayoutManager cardStackLayoutManager;
    private Button btnSettings;
    protected Adopter currentUser;
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_page);

        btnSettings = findViewById(R.id.btnSettings);
        cardStackView = findViewById(R.id.card_stack_view);
        //Get the current user if any...
        auth = FirebaseAuth.getInstance();
        uid = auth.getUid();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(uid);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentUser = dataSnapshot.getValue(Adopter.class);
                Toast.makeText(SwipePage.this, "Welcome: " + currentUser.getAgeFilter().get(0).toString(), Toast.LENGTH_SHORT).show();


                cardStackLayoutManager = new CardStackLayoutManager(SwipePage.this, new CardStackListener() {
                    @Override
                    public void onCardDragging(Direction direction, float ratio) {

                    }

                    @Override
                    public void onCardSwiped(Direction direction) {
                        if (direction == Direction.Left) {
                            // Dislike a pet
                            Toast.makeText(SwipePage.this, "You disliked this pet", Toast.LENGTH_LONG).show();
                        } else if (direction == Direction.Right) {
                            //Like a pet
                            Toast.makeText(SwipePage.this, "You liked this pet", Toast.LENGTH_LONG).show();

                            ArrayList<Animal> allAnimals = cardStackAdapter.getAnimals();

                            currentUser.addLikedAnimal(allAnimals.get(cardStackLayoutManager.getTopPosition() - 1));

                            mDatabase.setValue(currentUser);
                        }

                    }

                    @Override
                    public void onCardRewound() {

                    }

                    @Override
                    public void onCardCanceled() {

                    }

                    @Override
                    public void onCardAppeared(View view, int position) {

                    }

                    @Override
                    public void onCardDisappeared(View view, int position) {

                    }
                });


                cardStackLayoutManager.setStackFrom(StackFrom.None);
                cardStackLayoutManager.setVisibleCount(3);
                cardStackLayoutManager.setTranslationInterval(8.0f);
                cardStackLayoutManager.setTranslationInterval(0.95f);
                cardStackLayoutManager.setSwipeThreshold(0.3f);
                cardStackLayoutManager.setMaxDegree(20.0f);


                cardStackLayoutManager.setDirections(Direction.FREEDOM);
                cardStackLayoutManager.setCanScrollHorizontal(true);
                cardStackLayoutManager.setSwipeableMethod(SwipeableMethod.Manual);
                cardStackLayoutManager.setOverlayInterpolator(new LinearInterpolator());
                cardStackView.setLayoutManager(cardStackLayoutManager);

                cardStackAdapter = new CardStackAdapter(addTempList(), currentUser);
                cardStackView.setAdapter(cardStackAdapter);
                cardStackView.setItemAnimator(new DefaultItemAnimator());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };

        mDatabase.addListenerForSingleValueEvent(valueEventListener);

    }

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // R.menu.mymenu is a reference to an xml file named mymenu.xml which should be inside your res/menu directory.
        // If you don't have res/menu, just create a directory named "menu" inside res
        getMenuInflater().inflate(R.menu.adopter_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.btnSettings) {
            Intent settings = new Intent(SwipePage.this, Settings.class);
            startActivity(settings);
        } else if (id == R.id.viewAdopterProfile) {
            Intent profile = new Intent(SwipePage.this, Profile.class);
            startActivity(profile);
        } else if (id == R.id.viewLikedPets) {
            Intent likedPets = new Intent(SwipePage.this, LikedPets.class);
            startActivity(likedPets);
        }
        return super.onOptionsItemSelected(item);
    }


    public ArrayList<Animal> addTempList() {
        ArrayList<Animal> animals = new ArrayList<>();

        animals.add(new Animal("Steve", R.drawable.kitten, 1, "Cat"));
        animals.add(new Animal("Eevee", R.drawable.eevee, 8, "Dog"));
        animals.add(new Animal("Bud", R.drawable.goldenretriever, 4, "Dog"));
        animals.add(new Animal("Iron", R.drawable.iron, 3, "Dog"));

        return animals;
    }
}