package com.company.crazyeights;

import com.company.deck.Card;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards = new ArrayList<>();
    private Actor player;

    public Hand(Actor player) {
        this.player = player;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public String displayHand() {
        StringBuilder output = new StringBuilder();
        for (Card card : cards) {
            output.append(card.display()).append(" ");
        }
        return output.toString().trim();
    }

    public int getAction(Card activeCard) {
        return player.getAction(activeCard, cards);
    }

    public int size() {
        return cards.size();
    }

    public String getName() {
        return player.getName();
    }


    public Card removeCard(int index) {
        return cards.remove(index);
    }

    public void discardHand() {
        cards.clear();
    }

    public Card getCard(int index){
        return cards.get(index);
    }
}