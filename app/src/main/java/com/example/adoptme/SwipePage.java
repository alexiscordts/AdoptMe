package com.example.adoptme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;

import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import com.example.adoptme.Accounts.Animal;
import com.example.adoptme.Accounts.UserModel;
import com.example.adoptme.Adapters.CardStackAdapter;
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
    private UserModel currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_page);

        cardStackView = findViewById(R.id.card_stack_view);

        //Get the current user if any...


        //for now lets just create a basic user..
        currentUser = new UserModel("vtorres@iastate.edu", "Veronica", "515-402-7893", R.drawable.goldenretriever);

        //Test age filtering...
//        currentUser.changeAgeFilter(3,8);
//        currentUser.addTypeFilter("Dog");


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
        cardStackAdapter = new CardStackAdapter(addTempList(), currentUser);
        cardStackView.setLayoutManager(cardStackLayoutManager);
        cardStackView.setAdapter(cardStackAdapter);
        cardStackView.setItemAnimator(new DefaultItemAnimator());


    }



    public ArrayList<Animal> addTempList(){
        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Steve",R.drawable.kitten, 1, "Cat"));
        animals.add(new Animal("Eevee",R.drawable.eevee, 8, "Dog"));
        animals.add(new Animal("Bud",R.drawable.goldenretriever, 4, "Dog"));
        animals.add(new Animal("Iron",R.drawable.iron, 3, "Dog"));

        return animals;
    }
}