package com.Fanny.projektarbete;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import static com.Fanny.projektarbete.Color.*;

public class Menu {

    Scanner scanner = new Scanner(System.in);
    Player player;
    Dealer dealer;
    List<Card> deckOfCards;
    int pSum = 0;
    int dSum = 0;
    Deck deck;


    // Constructor
    public Menu(Player player, Dealer dealer, List<Card> deckOfCards, Deck deck) {
        this.player = player;
        this.dealer = dealer;
        this.deckOfCards = deckOfCards;
        this.deck = deck;

    }
    //Using a welcome method so the player do not get the welcome message every round
    public void welcome(){
        System.out.println("(♥)(♤)(♢)(♧)(♥)(♤)(♢)(♧)(♥)(♤)(♢)(♧)(♥)(♤)(♢)(♧)");
        System.out.println(RED_BOLD_BRIGHT+"Welcome to Blackjack!"+ RESET + "\nWhat is your name?");
        player.setName(scanner.nextLine());

        player.pMoney();
    }
    public void mainMenu() {

        boolean isPlaying = true;

        do {
            System.out.println("(♥)(♤)(♢)(♧)(♥)(♤)(♢)(♧)(♥)(♤)(♢)(♧)(♥)(♤)(♢)(♧)");
            System.out.println( PURPLE+ player.getName() +RESET+ " select a choice bellow:\n");
            System.out.println("""
                    1 - Start game \s
                    \s
                    0 - Exit Game
                    """);
            System.out.println("(♥)(♤)(♢)(♧)(♥)(♤)(♢)(♧)(♥)(♤)(♢)(♧)(♥)(♤)(♢)(♧)");
            switch (scanner.next()) {
                case "1" -> {
                    deck.generateDeck().clear(); // clears the former deck
                    deck.generateDeck(); //generates a new deck of cards

                    player.pHand.clear();
                    dealer.dHand.clear();


                    pSum = 0;
                    dSum = 0;

                    player.betting(); //Player sets a bet for the game

                    Collections.shuffle(deckOfCards);
                    player.firstDraw(deckOfCards);
                    for (int i = 0; i < player.pHand.size(); i++) {
                        pSum += player.pHand.get(i).getCardValue();
                    }
                    System.out.println("(♥)(♤)(♢)(♧)(♥)(♤)(♢)(♧)(♥)(♤)(♢)(♧)(♥)(♤)(♢)(♧)\n");
                    System.out.println("Player got: \n " + player.pHand);
                    System.out.println("Total value of your cards are: " + pSum);
                    System.out.println("------------------------------------------ \n");

                    dealer.firstDraw(deckOfCards);
                    for (int i = 0; i < dealer.dHand.size(); i++) {
                        dSum += dealer.dHand.get(i).getCardValue();
                    }

                    System.out.println("Dealer got: \n " + dealer.dHand);
                    System.out.println("Total value of dealers cards are: " + dSum);
                    System.out.println("----------------------------------------- \n");

                    blackJack();

                    menuStartGame();
                }

                case "0" -> {
                    System.out.println("******************************************************");
                    System.out.println("You have now exit the game.\nThank you for playing!");
                    isPlaying = false;
                    System.exit(0);

                }

                default -> System.out.println("Error select a valid choice");
            }

        } while (isPlaying);

    }


    public void menuStartGame() {
        System.out.println( PURPLE+ player.getName() +RESET+ " select a choice bellow:");
        System.out.println("""
                1 - Hit \s
                2 - Stay \s
                """);

        switch (scanner.next()) {
            case "1" -> {
                player.draw(deckOfCards);
                System.out.println("Player got: \n" + player.pHand);
                pSum += player.pHand.get(player.pHand.size() - 1).getCardValue();
                System.out.println("Total value of your cards are: " + pSum);
                System.out.println("----------------------------------------- \n");


                if (pSum > 21){
                    System.out.println("Sorry you got over 21 you lost "+ PURPLE + player.getName()+ RESET +".");
                    System.out.println("You lost: " + RED + player.getBet()+ RESET +"€");
                    player.setMoney(player.getMoney() - player.getBet());
                    System.out.println("You now have " + GREEN + player.getMoney() + RESET + "€ left to play for.");
                    System.out.println("-----------------------------------------");
                    player.gameOver(); //if player lost all their money they get game over
                    mainMenu();
                }

                blackJack(); //check if player got blackjack every time they draw a card
                menuStartGame();

            }

            case "2" -> {
                boolean dealerPlaying = true;
                System.out.println("You choose to stay, now its the dealers turn");
                do {
                    dealer.draw(deckOfCards);
                    System.out.println("Dealer got: \n" + dealer.dHand);
                    dSum += dealer.dHand.get(dealer.dHand.size() - 1).getCardValue();
                    System.out.println(dSum);
                    System.out.println("----------------------------------------- \n");
                    blackJack(); //Check if dealer get blackjack for every card thew draw

                    if (dSum > 21){
                        System.out.println("Dealer bust!");
                        System.out.println(PURPLE + player.getName() + RESET + " you won!");
                        System.out.println("You won: " + GREEN + player.getBet() + RESET +"€");
                        player.setMoney(player.getMoney() + player.getBet());
                        System.out.println("You now have " +GREEN+ player.getMoney() + RESET + "€ left to play for.");
                        System.out.println("-----------------------------------------");
                        mainMenu();
                    }

                   else if (dSum <= 21 && dSum >= 18 ) {
                        System.out.println("Dealer stays");

                        if (dSum > pSum) {
                            System.out.println("Dealer wins");
                            player.gameOver();
                            System.out.println("You lost: " + RED + player.getBet()+ RESET +"€");
                            player.setMoney(player.getMoney() - player.getBet());
                            System.out.println("You now have " + GREEN + player.getMoney() + GREEN + "€ left to play for.");
                            System.out.println("-----------------------------------------");

                        } else if (dSum == pSum) {
                            System.out.println("It's a tie! You did not lose nor win any money.");


                        }else {
                            System.out.println(PURPLE+ player.getName() + RESET + " you won!");
                            System.out.println("You won: " +GREEN+ player.getBet() + RESET + "€");
                            player.setMoney(player.getMoney() + player.getBet());
                            System.out.println("You now have " + GREEN+ player.getMoney()+ RESET + "€ left to play for.");
                            System.out.println("-----------------------------------------");
                        }
                        dealerPlaying = false;

                    }
                } while (dealerPlaying);
            }
        }
    }

    public void blackJack() {

        if (pSum == 21 && dSum < 21) {
            System.out.println("YOU GOT BLACKJACK!");
            System.out.println("You won: " + player.getBet());
            player.setMoney(player.getMoney() + player.getBet());
            System.out.println("You now have: " + player.getMoney());
            System.out.println("-----------------------------------------");
            mainMenu();

        } else if (dSum == 21 && pSum < 21) {
            System.out.println("Dealer got BlackJack, you lost");
            System.out.println("You lost: " + player.getBet());
            player.setMoney(player.getMoney() - player.getBet());
            System.out.println("You now have: " + player.getMoney());
            System.out.println("-----------------------------------------");
            mainMenu();
        }


    }


}
