package com.company.crazyeights;

import com.company.actor.Player;
import com.company.deck.Card;
import com.company.deck.Deck;
import com.company.deck.StandardDeck;
import com.company.utils.Console;
import com.sun.tools.jconsole.JConsoleContext;

import java.util.ArrayList;
import java.util.List;

public class Table {

    private List<Hand> hands = new ArrayList<>(); //Hand has a player
    private Card activeCard;
    private Deck deck;
    private Card userSelectedCard;
    private int counter = 52;


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
        firstCardinPlay();
        while (turn()) ;
    }


    private boolean turn() {
        for (Hand activeHand : hands) {
            System.out.println(activeHand.getName() + " |" + " " + activeHand.displayHand() + " | ");
            System.out.println("Card in Play: " + activeCard.display());
            int result = activeHand.getAction(activeCard);
            if (result == 1) {
                activeHand.addCard(deck.draw());
                counter--;
                checkDeckSize();
                deck.displayDeck();
                System.out.println(activeHand.getName() + " " + "| " + activeHand.displayHand() + " | ");
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
                    if (userSelectedCard.getRank() == 8) {
                        int newSuit = Console.getInt("Choose a suit:\n1. Clubs\n2. Spades\n3. Hearts\n4. Diamonds ", 1,
                                4 ,
                                "Invalid");

                       // newEightSuit =
                        // change suit of Card in play.
                    }
                    result2 = validCard();
                } while (result2);
                activeCard = activeHand.removeCard(num);
                deck.addCardToDeck(activeCard); // add card back to deck
                deck.displayDeck(); // debug
                counter--;
                checkDeckSize(); // in the case no one is discarding cards.
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
            return true;
        }
    }

    private void deal() {
        for (int count = 0; count < 5; count++) {
            for (Hand activeHand : hands) {
                activeHand.addCard(deck.draw());
                counter--;
                checkDeckSize();
            }
        }
    }

    private void firstCardinPlay() {
        activeCard = deck.draw();
        counter--;
        checkDeckSize();
    }

    private void checkDeckSize() {
        if (deck.size() == 0) {
            System.out.println(" Game over due to players holding cards!");
            System.exit(0);
        }
        if (counter == 1) {
            deck.shuffle();
        }
    }

//    public int startingDeckSize() {
//        return counter = deck.size();
//    }

}


