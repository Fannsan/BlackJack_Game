package com.Fanny.projektarbete;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static com.Fanny.projektarbete.Color.*;
public class Player {
    private String name;

    private int money;

    private int bet;

    List<Card> pHand = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);

    //Constructor
    public Player(String name, int money, int bet) {
        this.name = name;
        this.money = money;
        this.bet = bet;
    }

    public void pMoney(){
        System.out.println("How much money in € do you have to play for "+ PURPLE + name + RESET + "?" );
        setMoney(scanner.nextInt());
        System.out.println("You have "+GREEN+ getMoney() + RESET + "€ to play for. \n"); // -use for (player.getMonet - bet )
    }

    public int betting(){
        System.out.println("Place your bet, minimum is 2€ and maximum is 500€");
        setBet(scanner.nextInt());
        if(bet <= 500 && bet >= 2 && bet <= money)
        {
            System.out.println("You placed a bet worth " + GREEN + getBet() + RESET + "€.\n");
        }

        else{
            System.out.println("You have " + GREEN + getMoney() + RESET + "€ to play for.\n");
            betting();
        }

        return bet;
    }

    public void firstDraw (List < Card > deckOfCards) {
        pHand.add(deckOfCards.get(0));
        deckOfCards.remove(0);
        pHand.add(deckOfCards.get(0));
        deckOfCards.remove(0);
    }


    public void draw (List < Card > deckOfCards) {
            pHand.add(deckOfCards.get(0));
            deckOfCards.remove(0);
        }

    public void gameOver(){
        if (money <= 0) {
            System.out.println("\nYou lost all your money, Game over!");
            System.out.println("(♥)(♤)(♢)(♧)(♥)(♤)(♢)(♧)(♥)(♤)(♢)(♧)(♥)(♤)(♢)(♧)");
            System.exit(0);
        }
    }

    //Getters and setters to use private variables outside of the class
    public String getName () {
            return name;
        }

    public void setName (String name){
            this.name = name;
        }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }
}

