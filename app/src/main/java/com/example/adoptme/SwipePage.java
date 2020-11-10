package com.example.adoptme;

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

import com.example.adoptme.Accounts.Animal;
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
    private CardStackLayoutManager  cardStackLayoutManager;
    private Button btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_page);

        btnSettings = findViewById(R.id.btnSettings);
//        btnSettings.setOnClickListener(this);

        cardStackView = findViewById(R.id.card_stack_view);
        cardStackLayoutManager = new CardStackLayoutManager(this, new CardStackListener() {
            @Override
            public void onCardDragging(Direction direction, float ratio) {

            }

            @Override
            public void onCardSwiped(Direction direction) {
                if(direction == Direction.Left){
                    // Dislike a pet
                    Toast.makeText(SwipePage.this, "You disliked this pet", Toast.LENGTH_LONG).show();
                }else if(direction == Direction.Right) {
                    //Like a pet
                    Toast.makeText(SwipePage.this, "You liked this pet", Toast.LENGTH_LONG).show();
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
        cardStackAdapter = new CardStackAdapter(addTempList());
        cardStackView.setLayoutManager(cardStackLayoutManager);
        cardStackView.setAdapter(cardStackAdapter);
        cardStackView.setItemAnimator(new DefaultItemAnimator());


    }

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // R.menu.mymenu is a reference to an xml file named mymenu.xml which should be inside your res/menu directory.
        // If you don't have res/menu, just create a directory named "menu" inside res
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.btnSettings) {
            Intent settings = new Intent(SwipePage.this, Settings.class);
            startActivity(settings);
        }
        return super.onOptionsItemSelected(item);
    }


    public ArrayList<Animal> addTempList(){
        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Steve",R.drawable.kitten, "1", "Cat"));
        animals.add(new Animal("Eevee",R.drawable.eevee, "8", "Dog"));
        animals.add(new Animal("Bud",R.drawable.goldenretriever, "4", "Dog"));
        animals.add(new Animal("Iron",R.drawable.iron, "3", "Dog"));

        return animals;
    }
//
//    /**
//     * On settings click, handles redirecting the user to the settings page
//     *
//     * @param view
//     */
//    @Override
//    public void onClick(View view) {
//        if (view.getId() == R.id.btnSettings) {
//            Intent settings = new Intent(SwipePage.this, Settings.class);
//            startActivity(settings);
//        }
//    }
}