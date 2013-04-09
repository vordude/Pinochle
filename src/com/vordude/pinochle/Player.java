package com.vordude.pinochle;

abstract class Player {
    public final Integer position; 
    public final String team;
    
    public Player(Integer p, String t) {
        position = p;
        team = t;
    }
    
    abstract void play();
}                                                                 