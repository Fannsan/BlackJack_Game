package com.Fanny.projektarbete;

import java.util.Scanner;

public class Card {

    private String cardSymbol;
    private String cardValue;



    public String getCardSymbol() {
        return cardSymbol;
    }

    public void setCardSymbol(String cardSymbol) {
        this.cardSymbol = cardSymbol;
    }

    public int getCardValue() {

         return switch (cardValue) {
            case "Ace" -> 11;

             case "Jack", "Queen", "King" -> 10;


             default -> Integer.parseInt(cardValue);

        };

    }


    public void setCardValue(String cardValue) {
        this.cardValue = cardValue;
    }

    @Override
    public String toString() {
        return '{' +
                "Card value is: '" + cardValue + '\'' +
                 cardSymbol +
                '}';
    }
}
