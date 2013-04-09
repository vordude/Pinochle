package com.vordude.pinochle;

public class Pinochle {
	Hand[] hands = new Hand[4];
	Integer turn, dealer;
	Integer us, them = 0;
	Player[] thePlayers = new Player[4];
	
	public Pinochle() {
	    thePlayers[0] = new HumanPlayer(0, "us");
	    thePlayers[1] = new AiPlayer(1, "them");
	    thePlayers[2] = new AiPlayer(1, "us");
	    thePlayers[3] = new AiPlayer(3, "them");
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
		bid();
		trade();
		for (int i = 0; i < 12; i++) {
			trick();
		}
		scoreRound();
		if (checkWin()) {
			//end game
		}
		
	}
	
	private void bid() {
	}

	private void trade() {
	}

	public void trick() {
	  for (int i = 0; i < 4; i ++) {
		  thePlayers[turn].play();
		  turnNext(turn);
	  }
	}

	public void scoreRound() {
	}

	private Boolean checkWin() {
		return false;
	}
	
	private void turnNext(Integer i) {
		i++;
		if (turn > 3) {
			i = 0;
		}
	}
}
