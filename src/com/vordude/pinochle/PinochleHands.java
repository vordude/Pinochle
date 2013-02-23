package com.vordude.pinochle;

import android.util.Log;

public class PinochleHands {
    private Hand south, west, north, east;

    public PinochleHands() {
        north = new PinochleHand();
        east = new PinochleHand();
        south = new PinochleHand();
        west = new PinochleHand();
        
        Deck pDeck = new PinochleDeck().deck;
        pDeck.shuffle();
        Integer i = 0;
        while(i < 12) {
            south.addCard(pDeck.dealCard());
            west.addCard(pDeck.dealCard());
            north.addCard(pDeck.dealCard());
            east.addCard(pDeck.dealCard());
            i++;
        }
        sort(south, west, north, east);
    }
    public Hand south(){
        return south;
    }
    public Hand west(){
        return west;
    }
    public Hand north(){
        return north;
    }
    public Hand east() {
        return east;
    }
    
    
    private void sort(Hand south, Hand west, Hand north, Hand east) {
        south.sort();
        west.sort();
        north.sort();
        east.sort();
    }
}
