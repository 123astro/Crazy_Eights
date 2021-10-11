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

    private List<Hand> hands = new ArrayList<>(); //Hand has a player
    private Card activeCard;
    private Deck deck;
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
        while (turn());
    }

    private boolean turn() {
        for (Hand activeHand : hands) {
            boolean result2 = true;
            do {
                System.out.println(activeHand.getName() + " |" + " " + activeHand.displayHand() + " | ");
                System.out.println("Card in Play: " + activeCard.display());
                int result = activeHand.getAction(activeCard);
                if (result == 1) {
                    counter--;
                    activeHand.addCard(deck.draw());
                    checkDeckSize();
                   // deck.displayDeck();
                    System.out.println(activeHand.getName() + " " + "| " + activeHand.displayHand() + " | ");
                    for (int i = 0; i < 4; i++) {
                        System.out.println();
                    }
                    result2 = false;
                } else if (result == 2) {
                    int num;
                    num = Console.getInt("Select a card 1 through " + (activeHand.size()), 1,
                            activeHand.size(),
                            "Enter " +
                                    "valid " +
                                    "number");
                    num--;
                    userSelectedCard = activeHand.getCard(num);
                    if (userSelectedCard.getRank() == 8) {
                        int newSuit = Console.getInt("Choose a suit:\n1. Clubs\n2. Spades\n3. Hearts\n4. Diamonds ", 1,
                                4,
                                "Invalid");
                    }
                        result2 = validCard();
                    if (userSelectedCard.getSuit() == activeCard.getSuit()) {
                        activeCard = activeHand.removeCard(num);
                        deck.addCardToDeck(activeCard); // add card back to deck
                        //deck.displayDeck(); // debug
                        counter--;
                    }
                    checkDeckSize(); // in the case no one is discarding cards. edge case
                    System.out.println(activeHand.getName() + " |" + " " + activeHand.displayHand() + " | ");
                    for (int i = 0; i < 4; i++) {
                        System.out.println();
                    }
                } else {
                    System.out.println("Thanks for playing!");
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

    private boolean validCard() {
        if (activeCard.getRank() == userSelectedCard.getRank() || activeCard.getSuit().equals(userSelectedCard.getSuit())) {
            activeCard = userSelectedCard;
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
        for (int count = 0; count < 1; count++) {
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
            deck.shuffle();
        }
    }

//    public int startingDeckSize() {
//        return counter = deck.size();
//    }

}


