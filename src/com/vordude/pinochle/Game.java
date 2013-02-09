package com.vordude.pinochle;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.Resources;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;

public class Game extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        PinochleDeck pDeck = new PinochleDeck();
        Deck deck = pDeck.deck;
        deck.shuffle();
        
        Hand hand = new PinochleHand();
        
        Integer i = 0;
        while (i < 12) {
            Card c = deck.dealCard();
            hand.addCard(c);
            i++;
        }
        
        setContentView(R.layout.activity_game);
        hand.sort();
        Integer j = 0;
        while (j < 12) {
            Card card = hand.getCard(j);
            Log.w("stuf", card.toString());
            Integer id = getResources().getIdentifier(card.getCardImageResourceName(), "drawable", getApplicationContext().getPackageName());        
            //ImageView view = (ImageView) findViewById(R.id.card);
            Integer imageViewId = getResources().getIdentifier("card" + j ,"id", getApplicationContext().getPackageName());
            Log.w("stuf", imageViewId.toString());
            ImageView view = (ImageView) findViewById(imageViewId);
            view.setImageResource(id);   
            j++;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_game, menu);
        return true;
    }
}
