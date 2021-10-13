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
    private Card crazyEightCard = new Card("\u2667", 8);
    private Card activeCard;
    private final Deck deck;
    private Card userSelectedCard;
    private int counter;

    public Table() {
        deck = new StandardDeck();
        System.out.println("CRAZY EIGHTS");
        System.out.println();
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
        activeCard = deck.draw();
        counter--;
        while (turn()) ;
    }

    private boolean turn() {
        for (Hand activeHand : hands) {
            boolean result2 = true;
            do {
                cardInPlay(activeHand);
                int result = activeHand.getAction();
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
                    int num = activeHand.getAction(activeHand);
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

    private void removeHandCard(Hand activeHand, int num) {
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

    private void cardInPlay(Hand activeHand) {
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
            }
        }
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

            String suit = switch (newSuit) {
                case 1 -> "\u2667";
                case 2 -> "\u2664";
                case 3 -> "\u2665";
                case 4 -> "\u2666";
                default -> "";
            };
            activeCard = activeHand.removeCard(num);
            deck.addCardToDeck(activeCard);
            crazyEightCard = new Card(suit, 8);
            activeCard = crazyEightCard;
        }
    }
}



