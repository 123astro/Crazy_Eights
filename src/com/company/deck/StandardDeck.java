package com.company.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StandardDeck implements Deck {
    private List<Card> cards = new ArrayList<>(); // cards in list
    final private int[] VALUES = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    final private String[] SUITS = {"\u2664", "\u2665", "\u2666", "\u2667"}; // unicode characters

    public StandardDeck() {  // constructor
        for (String suit : SUITS) {
            for (int val : VALUES) {
                cards.add(new Card(suit, val));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards); //shuffling method in collections Fisher-Yates
    }

    public Card draw() {  // return a card from cards list. Datatype Card
        return cards.remove(cards.size() - 1); // grab from the last position in the list and return with remove
    }

    public int size(){
        return cards.size();
    }

    public void addCardToDeck(Card oldCard){
        cards.add(0,oldCard);
    }

    public void displayDeck(){
        System.out.println(cards);
    }
}