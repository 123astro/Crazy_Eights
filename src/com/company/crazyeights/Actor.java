package com.company.crazyeights;

import com.company.deck.Card;

import java.util.List;

public interface Actor {

    String getName();

    int getAction();

    int chooseCard(List<Card> cards, Card activeCard);

    int setSuit(List<Card> cards);


}
