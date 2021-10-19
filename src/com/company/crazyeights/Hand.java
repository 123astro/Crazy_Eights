package com.company.crazyeights;

import com.company.deck.Card;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards = new ArrayList<>(); // cards are my hand
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

    public int size() {
        return cards.size();
    }

    public String getName() {
        return player.getName();
    }

    public Card removeCard(int index) {
        return cards.remove(index);
    }

    public Card getCard(int index){
        return cards.get(index);
    }

    public int getAction() {  // passthru to use the getAction in player.
        return player.getAction();
    }

    public int chooseCard(Card activeCard){
        return player.chooseCard(cards,activeCard);
    }

    public int setSuit(){
      return player.setSuit(cards);
    }


}
