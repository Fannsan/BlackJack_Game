package com.Fanny.projektarbete;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Deck deckTemplate = new Deck();
        Player player = new Player("", 0,0);
        Dealer dealer = new Dealer();

        List<Card> deckOfCards = deckTemplate.generateDeck();// Sorted by default

        Menu menu = new Menu(player, dealer, deckOfCards, deckTemplate);

        menu.welcome();
        menu.mainMenu();
        menu.menuStartGame();

    }
}
