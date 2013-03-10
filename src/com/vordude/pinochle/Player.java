package com.vordude.pinochle;

public class Player {
    private String position;
    private Hand hand;
    
    public Player(String p, Hand h) {
        position = p;
        hand = h;
    }
    
    public Hand getHand() {
        return hand;
    }
}                                                                 