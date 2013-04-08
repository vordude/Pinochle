package com.vordude.pinochle;

import java.util.HashMap;
import java.util.Iterator;

public class Pinochle {
	Hand[] hands = new Hand[4];	
	
	public Pinochle() {
	}
	
	public void deal() {
		Deck pinochleDeck = new PinochleDeck().deck;
        pinochleDeck.shuffle();
        Integer i = 0;
        while(i < 12) {
        	for(Hand h : hands ){
        		   h.addCard(pinochleDeck.dealCard());
        		}
        }
	}
	
	
	public void trick() {
		
	}
	
	
	
	
	public void scoreRound() {
		
	}
	
	
	
}
