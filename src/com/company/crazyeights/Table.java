package com.company.crazyeights;

import com.company.actor.Player;
import com.company.deck.Card;
import com.company.deck.Deck;
import com.company.deck.StandardDeck;
import com.company.utils.Console;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Table {

    private final List<Hand> hands = new ArrayList<>(); //Hand has a player
    private Card crazyEightCard;
    private Card activeCard;
    private final Deck deck;
    private Card userSelectedCard;
    private int counter;

    public Table() {
        deck = new StandardDeck();
        int playerCount = Console.getInt("How many players?", 1, 5, "invalid player selection");
        for (int count = 0; count < playerCount; count++) {  // created loop at add the number of players
            Player newPlayer = new Player("Player" + (count + 1)); //create new player 1 thru 6
            hands.add(new Hand(newPlayer)); // instantiating a hand for the new player and adding to the list
        }

    }

    public void playGame() {
        counter = deck.size();
        deck.shuffle();
        deal();
        firstCardinPlay();
        crazyEightCard = new Card("u2664", 1);
        while (turn()) ;
    }

    private boolean turn() {
        for (Hand activeHand : hands) {
            boolean result2 = true;
            //deck.displayDeck();
            do {
                cardInPlay(activeHand);
                int result = activeHand.getAction(activeCard);
                if (result == 1) { //Draw?
                    counter--;
                    activeHand.addCard(deck.draw());
                    checkDeckSize(); //Checking for shuffle or make sure deck still has cards.
                    System.out.println(activeHand.getName() + " " + "| " + activeHand.displayHand() + " | ");
                    for (int i = 0; i < 3; i++) { //print blank lines
                        System.out.println();
                    }
                    result2 = false;
                } else if (result == 2) { //Play a card?
                    int num;
                    num = Console.getInt("Select a card 1 through " + (activeHand.size()), 1,
                            activeHand.size(),
                            "Enter " +
                                    "valid " +
                                    "number");
                    num--;
                    userSelectedCard = activeHand.getCard(num);
                    checkForEight(activeHand, num);
                    removeHandCard(activeHand, num);
                    result2 = validCard();
                    checkDeckSize(); // in the case no one is discarding cards. edge case
                    System.out.println(activeHand.getName() + " |" + " " + activeHand.displayHand() + " | ");
                    for (int i = 0; i < 4; i++) {
                        System.out.println();
                    }
                } else { // Quit
                    System.out.println("You selected to quit. Thanks for playing!");
                    System.exit(0);
                }
            } while (result2);

            if (activeHand.size() == 0) {
                System.out.println(activeHand.getName() + " is the Winner");
                System.exit(0);
            }
        }
        return true;
    }

    private void removeHandCard(Hand activeHand, int num){
        if ((userSelectedCard.getSuit().equals(activeCard.getSuit()) && activeCard.getRank() != 8) ||
                (userSelectedCard.getRank() == activeCard.getRank()) && activeCard.getRank() != 8) {
            deck.addCardToDeck(activeCard); // add card back to deck
            userSelectedCard = activeHand.removeCard(num);
            activeCard = userSelectedCard;
            counter--;
        }
        if (userSelectedCard.getSuit().equals(activeCard.getSuit()) && activeCard.getRank() == 8 && userSelectedCard.getRank() != 8) {
            activeHand.removeCard(num);
            activeCard = userSelectedCard;
        }
    }

    private void cardInPlay(Hand activeHand){
        System.out.println(activeHand.getName() + " |" + " " + activeHand.displayHand() + " | ");
        if (activeCard.getRank() == 8 && crazyEightCard.getSuit() != activeCard.getSuit()) {
            System.out.println("Card in Play: " + crazyEightCard.display());
        } else {
            System.out.println("Card in Play: " + activeCard.display());
        }
    }

    private boolean validCard() {
        if (activeCard.getRank() == userSelectedCard.getRank() ||
                activeCard.getSuit().equals(userSelectedCard.getSuit()) ||
                crazyEightCard.getSuit().equals(userSelectedCard.getSuit())) {
            return false;
        } else if (!Objects.equals(activeCard.getSuit(), userSelectedCard.getSuit())) {
            System.out.println("*******Invalid Card*******");
            System.out.println("You picked " + userSelectedCard.display());
            System.out.println("Please pick another card from your hand or draw a card.");
            return true;
        }
        return true;
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
            System.out.println(" System Error => Game over due to players holding cards!");
            System.exit(0);
        }
        if (counter == 1) {
            deck.displayDeck();
            deck.shuffle();
            deck.displayDeck();
            counter = deck.size();
            System.out.println("***********************A re-shuffle has happened.****************************");
        }
    }

    private void checkForEight(Hand activeHand, int num) {
        if (userSelectedCard.getRank() == 8) {
            int newSuit = Console.getInt("Choose a suit:\n1. Clubs\n2. Spades\n3. Hearts\n4. Diamonds ", 1,
                    4,
                    "Invalid");
            if (newSuit == 1) {
                if (userSelectedCard.getSuit() == "u2667") {  //club
                    activeCard = userSelectedCard;
                    return;
                }
                activeCard = activeHand.removeCard(num);
                deck.addCardToDeck(activeCard);
                crazyEightCard = new Card("\u2667", 8);
                activeCard = crazyEightCard;
            }
            if (newSuit == 2) {
                if (userSelectedCard.getSuit() == "u2664") { //spade
                    activeCard = userSelectedCard;
                    return;
                }
                activeCard = activeHand.removeCard(num);
                deck.addCardToDeck(activeCard);
                crazyEightCard = new Card("\u2664", 8);
                activeCard = crazyEightCard;
            }
            if (newSuit == 3) {
                if (userSelectedCard.getSuit() == "u2665") { // heart
                    activeCard = userSelectedCard;
                    return;
                }
                activeCard = activeHand.removeCard(num);
                deck.addCardToDeck(activeCard);
                crazyEightCard = new Card("\u2665", 8);
                activeCard = crazyEightCard;
            }
            if (newSuit == 4) {
                if (userSelectedCard.getSuit() == "u2666") { //diamonds
                    activeCard = userSelectedCard;
                    return;
                }
                activeCard = activeHand.removeCard(num);
                deck.addCardToDeck(activeCard);
                crazyEightCard = new Card("\u2666", 8);
                activeCard = crazyEightCard;
            }
        }

    }
}


