package com.company.actor;


import com.company.crazyeights.Actor;
import com.company.crazyeights.Hand;
import com.company.deck.Card;
import com.company.utils.Console;

import java.util.List;

public class Player implements Actor {
    private final String name;
    private int actionsCount;

    public Player(String name) {
        this.name = name;
    }


    @Override
    public String getName() {
        return name;
    }


    @Override
    public int getAction(Card activeCard, List<Card> cards) {
       return Console.getInt("1. Draw?\n2. Play a card?", 1,2, "Please enter a valid number!");
    }


    private String getAvailableActions(Hand hand) {
        actionsCount = 3;
        StringBuilder output = new StringBuilder();
        output.append("0. Quit\n1. Discard\n2. Pull");
        return output.toString();
    }

}