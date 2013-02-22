package com.vordude.pinochle;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Game extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        PinochleDeck pDeck = new PinochleDeck();
        Deck deck = pDeck.deck;
        deck.shuffle();
        
        Hand north = new PinochleHand();
        Hand east = new PinochleHand();
        Hand south = new PinochleHand();
        Hand west = new PinochleHand();
        
        Integer i = 0;
        while (i < 12) {
            Card c = deck.dealCard();
            north.addCard(c);
            east.addCard(c);
            south.addCard(c);
            west.addCard(c);
            i++;
        }

        setContentView(R.layout.activity_game);
        south.sort();
        Integer j = 0;
        while (j < 12) {
            Card card = south.getCard(j);           
            Integer id = null;
            id = getResources().getIdentifier(card.getCardImageResourceName(), "drawable", getApplicationContext().getPackageName());        
            //ImageView view = (ImageView) findViewById(R.id.card);
            
            Integer imageViewId = null;
            imageViewId = getResources().getIdentifier("card" + j ,"id", getApplicationContext().getPackageName());
            
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
    
    public void cardTap(View v) {
       Integer id = v.getId();
       final ImageView imageView = (ImageView) findViewById(id);      
       Animation playCard = AnimationUtils.loadAnimation(this, R.anim.playcard);
       playCard.setFillAfter(true);
       imageView.startAnimation(playCard);
       
       playCard.setAnimationListener(new AnimationListener() {
                   public void onAnimationStart(Animation anim)
                   {
                   };
                   public void onAnimationRepeat(Animation anim)
                   {
                   };
                public void onAnimationEnd(Animation anim)
                   {
                       
                      imageView.setClickable(false);
                   };
               });
    }
}


