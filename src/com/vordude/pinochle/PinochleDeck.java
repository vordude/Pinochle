package com.vordude.pinochle;

import java.util.ArrayList;

public class PinochleDeck {
    public Deck deck;

    public PinochleDeck() {
        ArrayList<Suit> suits = new ArrayList<Suit>();
        suits.add(Suit.DIAMONDS);
        suits.add(Suit.HEARTS);
        suits.add(Suit.CLUBS);
        suits.add(Suit.SPADES);

        ArrayList<Rank> ranks = new ArrayList<Rank>();
        ranks.add(Rank.NINE);
        ranks.add(Rank.TEN);
        ranks.add(Rank.JACK);
        ranks.add(Rank.QUEEN);
        ranks.add(Rank.KING);
        ranks.add(Rank.ACE);

        deck = new Deck();

        for(Suit suit : suits) {
            for(Rank rank : ranks) {
                Card c = new Card(suit, rank);
                deck.addCard(c);
                deck.addCard(c);
            }
        }
    }
}
