package com.Fanny.projektarbete;

import java.util.ArrayList;
import java.util.List;

public class Dealer {


    List<Card> dHand = new ArrayList<>();


    public void firstDraw (List < Card > deckOfCards) {
        dHand.add(deckOfCards.get(0));
        //pHand.add(deckOfCards.get(deckOfCards.size() -1));
        deckOfCards.remove(0);
        dHand.add(deckOfCards.get(0));
        //pHand.add(deckOfCards.get(deckOfCards.size() -1));
        deckOfCards.remove(0);


    }
    public void draw(List<Card> deckOfCards) {

        dHand.add(deckOfCards.get(0));
        //dHand.add(deckOfCards.get(deckOfCards.size() -1));
        deckOfCards.remove(0);


    }

}
