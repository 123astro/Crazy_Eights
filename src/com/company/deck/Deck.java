package com.company.deck;

public interface Deck {
    void shuffle();
    Card draw();
    int size();
    void addCardToDeck(Card oldCard);
    void displayDeck();
}
