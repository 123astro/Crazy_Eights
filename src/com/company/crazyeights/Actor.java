package com.company.crazyeights;

import com.company.deck.Card;

import java.util.List;

public interface Actor {

    String getName();

    int getAction();

    int getAction(Hand activeHand);

    int setSuit();

}
