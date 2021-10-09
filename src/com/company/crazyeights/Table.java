package com.company.crazyeights;

import com.company.actor.Player;
import com.company.deck.Card;
import com.company.deck.Deck;
import com.company.deck.StandardDeck;
import com.company.utils.Console;
import com.company.crazyeights.Hand;

import java.util.ArrayList;
import java.util.List;

public class Table {

    private List<Hand> hands = new ArrayList<>();
    //private List<Actor> players = new ArrayList<>();
    private Card activeCard;
    private Deck deck;
    private Card userSelectedCard;


    public Table() {
        deck = new StandardDeck();
        int playerCount = Console.getInt("How many players?", 1, 5, "invalid player selection");
        for (int count = 0; count < playerCount; count++) {  // created loop at add the number of players
            Player newPlayer = new Player("Player" + (count + 1)); //create new player 1 thru 6
            hands.add(new Hand(newPlayer)); // instantiating a hand for the new player and adding to the list
        }
    }


    public void playGame() {
        deck.shuffle();
        deal();
        flipTopCard();
        while(playTurn());
    }

    private boolean playTurn(){
       return turn();
    }

    private boolean turn() {
        for (Hand activeHand : hands) {
            System.out.println(activeHand.getName() + " " + activeHand.displayHand() + " | ");
            int result = activeHand.getAction(activeCard);
            if (result == 1) {
                activeHand.addCard(deck.draw());
                System.out.println(activeHand.getName() + " " + activeHand.displayHand() + " | ");

            } else {
                int num = Console.getInt("Select a card 0 through " + (activeHand.size() -1), 0, activeHand.size() - 1,
                        "Enter " +
                                "valid " +
                                "number");
                userSelectedCard = activeHand.getCard(num);
                validCard();
                activeCard= activeHand.removeCard(num);
                System.out.println(activeHand.getName() + " " + activeHand.displayHand() + " | ");
                System.out.println("Card in Play: " + activeCard.display());

            }
        if(activeHand.size() == 0){
            System.out.println(activeHand.getName() + " is the Winner");
            return false;
        }
        }
        return true;
    }



    private void validCard() {
        if (activeCard.getRank() == userSelectedCard.getRank() || activeCard.getSuit().equals( userSelectedCard.getSuit())){
            activeCard = userSelectedCard;
        } else {
            System.out.println("*******Invalid Card*******");
            System.out.println("You picked " + userSelectedCard);
            System.out.println("Please pick another card from your hand or Draw a card.");
            displayActiveHand();
            System.out.println("Card in Play: " + activeCard.display());
            turn();
        }
    }

    private void displayActiveHand() {
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
        for (int count = 0; count < 1; count++) {
            for (Hand activeHand : hands) {
                activeHand.addCard(deck.draw());
            }
        }
    }


    private void determineWinner() {

    }

    private void flipTopCard() {
        activeCard = deck.draw();
        System.out.println("Card in Play: " + activeCard.display());
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


