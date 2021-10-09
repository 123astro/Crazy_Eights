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
    private Card activeCard;
    private Deck deck;
    private Card userSelectedCard;
    private Card trackFirstCard;
    private int counter;


    public Table() {
        deck = new StandardDeck();
        int playerCount = Console.getInt("How many players?", 1, 5, "invalid player selection");
        for (int count = 0; count < playerCount; count++) {  // created loop at add the number of players
            Player newPlayer = new Player("Player" + (count + 1)); //create new player 1 thru 6
            hands.add(new Hand(newPlayer)); // instantiating a hand for the new player and adding to the list
        }
    }

    public int startingDeckSize(){
        return counter = deck.size();
    }

    public void playGame() {
        startingDeckSize();
        System.out.println("Deck size: " + counter);
        deck.shuffle();
        deal();
        getCardInPlay();
        while (turn()) ;
    }

//    private void testForFirstCard(){
//        if(trackFirstCard == activeCard) {
//        }
//    }

    private boolean turn() {
        for (Hand activeHand : hands) {
            System.out.println(activeHand.getName() + " |" + " " + activeHand.displayHand() + " | ");
            System.out.println("Card in Play: " + activeCard.display());
            int result = activeHand.getAction(activeCard);
            if (result == 1) {
                activeHand.addCard(deck.draw());
                counter--;
                System.out.println(activeHand.getName() + " " + activeHand.displayHand() + " | ");
                for (int i = 0; i < 8; i++) {
                    System.out.println();
                }
            } else {
                int num;
                boolean result2;
                do {
                    num = Console.getInt("Select a card 1 through " + (activeHand.size()), 1,
                            activeHand.size(),
                            "Enter " +
                                    "valid " +
                                    "number");
                    num--;
                    userSelectedCard = activeHand.getCard(num);
                    result2 =  validCard();
                } while (result2);
                activeCard = activeHand.removeCard(num);
                counter--;
                System.out.println(activeHand.getName() + " |" + " " + activeHand.displayHand() + " | ");
                for (int i = 0; i < 8; i++) {
                    System.out.println();
                }
            }
            if (activeHand.size() == 0) {
                System.out.println(activeHand.getName() + " is the Winner");
                return false;
            }
        }
        return true;
    }


    private boolean validCard() {
        if (activeCard.getRank() == userSelectedCard.getRank() || activeCard.getSuit().equals(userSelectedCard.getSuit())) {
            activeCard = userSelectedCard;
            return false;
        } else {
            System.out.println("*******Invalid Card*******");
            System.out.println("You picked " + userSelectedCard.display());
            System.out.println("Please pick another card from your hand or Draw a card.");
            turn();
        }
        return true;
    }

    private void deal() {
        for (int count = 0; count < 1; count++) {
            for (Hand activeHand : hands) {
                activeHand.addCard(deck.draw());
                counter--;
            }
        }
    }

    private void getCardInPlay() {
        activeCard = deck.draw();
        trackFirstCard = activeCard;
        counter--;

    }

    private void checkDeckSize(){
        if(counter == 1){
            deck.shuffle();
        }
    }

    private boolean quit() {
        System.exit(0);
        return false;
    }


}


