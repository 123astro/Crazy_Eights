package com.company.crazyeights;

import com.company.actor.Player;
import com.company.deck.Card;
import com.company.deck.Deck;
import com.company.deck.StandardDeck;
import com.company.utils.Console;

import java.util.ArrayList;
import java.util.List;

public class Table {

    private List<Hand> hands = new ArrayList<>();
    //private List<Actor> players = new ArrayList<>();
    private Card activeCard;
    private Deck deck;


    public Table() {
        deck = new StandardDeck();
        int playerCount = Console.getInt("How many players?", 1, 5, "invalid player selection");
        for (int count = 0; count < playerCount; count++) {  // created loop at add the number of players
            Player newPlayer = new Player("Player" + (count + 1)); //create new player 1 thru 6
            hands.add(new Hand(newPlayer)); // instantiating a hand for the new player and adding to the list
        }
    }

//    public void playGame() {
//        while (true) {
//            playRound();
//        }
//    }

    public void playRound() {
        deck.shuffle();
        deal();
        displayTable();
        flipTopCard();
        turn();
        endRound();
    }

    private void turn() {
        for (Hand activeHand : hands) {
            int result = activeHand.getAction(activeCard);
            if (result == 1) {
                activeHand.addCard(deck.draw());
                displayTable();
            } else {
                activeCard = activeHand.removeCard(0);
                System.out.println("Deck Card: " + activeCard);
            }
        }
    }

    private void displayTable() {
        StringBuilder output = new StringBuilder();
        for (Hand activeHand : hands) {
            output.append(activeHand.getName()).append(" | ").append(activeHand.displayHand()).append(" | ");
        }
        System.out.println(output);
    }

    private void endRound() {
        for (Hand player : hands) {
            player.discardHand();
        }
    }

    private void deal() {
        for (int count = 0; count < 5; count++) {
            for (Hand activeHand : hands) {
                activeHand.addCard(deck.draw());
            }
        }
    }


    private void determineWinner() {

    }

    private void flipTopCard() {
        activeCard = deck.draw();
        System.out.println("Deck Card: " + activeCard);
    }

//    private boolean turn(Hand activeHand) {
//        byte action = activeHand.getAction(dealer);
//        if () {
//        }
//    }

    private boolean quit() {
        System.exit(0);
        return false;
    }


}


