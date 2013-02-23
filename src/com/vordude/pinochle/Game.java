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
        PinochleHands dealtHands =  new PinochleHands();
        setContentView(R.layout.activity_game);

        Hand south = dealtHands.south();
        
        Integer j = 0;
        while (j < 12) {
            Card card = south.getCard(j);           
            Integer id = null;
            id = getResources().getIdentifier(card.getCardImageResourceName(), "drawable", getApplicationContext().getPackageName());        

            Integer imageViewId = null;
            imageViewId = getResources().getIdentifier("card" + j ,"id", getApplicationContext().getPackageName());
            
            ImageView view = (ImageView) findViewById(imageViewId);
            view.setImageResource(id);
            
            j++;
        }
        aiPlay(dealtHands);
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
    
    private void aiPlay(PinochleHands dealtHands) {
        String cardWest = dealtHands.west().getCard(0).getCardImageResourceName();
        setCard(cardWest, "cardWest");
        
        String cardNorth = dealtHands.north().getCard(0).getCardImageResourceName();
        setCard(cardNorth, "cardNorth");
        
        String cardEast = dealtHands.east().getCard(0).getCardImageResourceName();
        setCard(cardEast, "cardEast");
    }
    
    private void setCard(String resourceName, String viewID) {
        Integer id = null;
        id = getResources().getIdentifier(resourceName, "drawable", getApplicationContext().getPackageName());        

        Integer imageViewId = getResources().getIdentifier(viewID, "id", getApplicationContext().getPackageName());
        ImageView view = (ImageView) findViewById(imageViewId);
        
        view.setImageResource(id);
    }
}


