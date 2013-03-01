package com.vordude.pinochle;

import java.util.ArrayList;
import java.util.HashMap;

import android.util.Log;

public class Meld {
    private Hand hand;
    private Integer score = 0;
    private Suit trump;
    private ArrayList<Suit> nonTrumpSuits = new ArrayList<Suit>();
    public Meld(Hand deltHand, Suit calledTrump) {
        hand = deltHand;
        trump = calledTrump;
        nonTrump();
        trumpRun();
        deece();
        plainMarriage();
        pinochle();
        around(Rank.JACK);
        around(Rank.QUEEN);
        around(Rank.KING);
        around(Rank.ACE);
    } 

    private void trumpRun() {
        Card jack = new Card(trump, Rank.JACK);
        Card queen = new Card(trump, Rank.QUEEN);
        Card king = new Card(trump, Rank.KING);
        Card ten = new Card(trump, Rank.TEN);
        Card ace = new Card(trump, Rank.ACE);

        Integer jacks = hand.containsCard(jack);
        Log.d("trump-j ", Integer.toString(jacks));
        Integer queens = hand.containsCard(queen);
        Log.d("trump-q ", Integer.toString(queens));
        Integer kings = hand.containsCard(king);
        Log.d("trump-k ", Integer.toString(kings));
        Integer tens = hand.containsCard(ten);
        Log.d("trump-t ", Integer.toString(tens));
        Integer aces = hand.containsCard(ace);
        Log.d("trump-a ", Integer.toString(aces));

        if (queens == 2 && kings == 2) {
            // Double marriage achieved, check for a double run
            if (jacks == 2 && aces == 2 && tens == 2) {
                meldScore("Double run in trump", 150);
            }
            else if(jacks > 0 && aces > 0 && tens > 0) {
                meldScore("Run in trump", 15);
                royalMarriage();
            }
            else {
                meldScore("Double Marriage in trump", 8);
            }
        }
        else if (queens > 0 && kings > 0) {
            //At least a royal marriage.
            if (jacks > 0 && aces > 0 && tens > 0) {
                meldScore("Run in Trump", 15);
            }
            else {
                royalMarriage();
            }
        }
    }

    private void royalMarriage() {
        meldScore("Royal Marriage", 4);
    }

    private void plainMarriage() {
        for (Suit s : nonTrumpSuits) {
            Card queen = new Card(s, Rank.QUEEN);
            Card king = new Card(s, Rank.KING);
            if (hand.containsCard(queen) > 0 && hand.containsCard(king) > 0) {
                if (hand.containsCard(queen) > 1 && hand.containsCard(king) > 1) {
                    meldScore("2 Plain Marriages in " + s.getName(), 4);
                }
                else {
                    meldScore("1 Plain Marriage in " + s.getName(), 2);
                }

            }
        }
    }

    private void around(Rank r) {
        Integer min = -1;
        for (Suit s : Suit.VALUES) {
            Card c = new Card(s,r);
            Integer suitNumber = hand.containsCard(c);
            if (min == -1) {
                min = suitNumber;
            }
            else if (min > suitNumber) {
                min = suitNumber;
            }
        }
        aroundScore(r, min);
    }

    private void aroundScore(Rank rank, Integer n) {
        Integer s = 0;
        String multiplier = "";
        // Apparently I can't switch on a string.  Java--
        if (rank.getSymbol().equals("j")) {
            switch (n) {
            case 1:  
                s = 4;
                multiplier = "";
                break;
            case 2:
                s = 40;
                multiplier = "Double";
                break;
            case 3:
                s = 60;
                multiplier = "Triple";
                break;
            case 4:
                s = 80;
                multiplier = "Quadruple";
                break;
            }
        }

        if (rank.getSymbol().equals("q")) {
            switch (n) {
            case 1:  
                s = 6;
                multiplier = "";
                break;
            case 2:
                s = 60;
                multiplier = "Double";
                break;
            case 3:
                s = 90;
                multiplier = "Triple";
                break;
            case 4:
                s = 120;
                multiplier = "Quadruple";
                break;                

            }
        }
        if (rank.getSymbol().equals("k")) {
            switch (n) {
            case 1:  
                s = 8;
                multiplier = "";
                break;
            case 2:
                s = 80;
                multiplier = "Double";
                break;
            case 3:
                s = 120;
                multiplier = "Triple";
                break;
            case 4:
                s = 160;
                multiplier = "Quadruple";
                break;
            }
        }
        if (rank.getSymbol().equals("a")) {
            switch (n) {
            case 1:  
                s = 10;
                multiplier = "";
                break;
            case 2:
                s = 100;
                multiplier = "Double";
                break;
            case 3:
                s = 150;
                multiplier = "Triple";
                break;
            case 4:
                s = 200;
                multiplier = "Quadruple";
                break;
            }
        }
        if (s > 0) {
            String prefix = "";
            if (multiplier != "") {
                prefix = multiplier + " ";
            }
            meldScore(prefix + rank.getName() + "s around", s);
        }
    }

    private void deece() {
        Card deece = new Card(trump, Rank.NINE);
        Integer numberOfDeece = hand.containsCard(deece);
        if (numberOfDeece > 0) {
            meldScore("Deece", hand.containsCard(deece));
        }
    }

    private void pinochle() {
        Card jack = new Card(Suit.DIAMONDS, Rank.JACK);
        Card queen = new Card (Suit.SPADES, Rank.QUEEN);

        Integer jacks = hand.containsCard(jack);
        Integer queens = hand.containsCard(queen);

        if (jacks == 2 && queens == 2) {
            meldScore("Double Pinochle", 30);
        }
        else if (jacks > 0 && queens > 0) {
            meldScore("Pinochle", 4);
        }


    }

    private void nonTrump() {
        nonTrumpSuits.add(Suit.HEARTS);
        nonTrumpSuits.add(Suit.DIAMONDS);
        nonTrumpSuits.add(Suit.SPADES);
        nonTrumpSuits.add(Suit.CLUBS);
        nonTrumpSuits.remove(trump);
    }


    private void meldScore(String description, Integer points) {
        score = score + points;
        meldScoreDescription.put(description, points);
        Log.i("Meld Score", Integer.toString(points) + " : " + description);
    }

    public Integer getMeldScore() {
        return score;
    }

    HashMap<String, Integer> meldScoreDescription = new HashMap<String, Integer>();
}
