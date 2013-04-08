package com.vordude.pinochle;

public class Pinochle {
	Hand[] hands = new Hand[4];
	Integer turn, dealer;
	
	public Pinochle() {
	}
	
	public void deal() {
		Deck pinochleDeck = new PinochleDeck().deck;
        pinochleDeck.shuffle();
        Integer i = 0;
        while(i < 12) {
        	for(Hand h : hands ) {
        		   h.addCard(pinochleDeck.dealCard());
        	}
        }
	}
	
	
	public void round() {
		while (int i = 0; i > 12, i++)
	}
	
	public void trick() {
		
	}
	
	public void scoreRound() {
		
	}
	
	private void turnNext(Integer i) {
		i++;
		if (turn > 3) {
			i = 0;
		}
	}
}
