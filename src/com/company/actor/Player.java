package com.company.actor;

import com.company.crazyeights.Actor;
import com.company.crazyeights.Hand;
import com.company.deck.Card;
import com.company.utils.Console;

import java.util.List;

public class Player implements Actor {
    private final String name;

    public Player(String name) {
        this.name = name;
    }



    @Override
    public String getName() {
        return name;
    }


    @Override
    public int getAction() {
        return Console.getInt("1. Draw?\n2. Play a card?\n3. Quit", 1, 3, "Please enter a valid number!");
    }

    @Override
    public int chooseCard(List<Card> cards, Card activeCard) {
        return Console.getInt("Select a card 1 through " + (cards.size()), 1,
                cards.size(),
                "Enter " +
                        "valid " +
                        "number");
    }
    @Override
    public int setSuit(List<Card> cards) {
            return Console.getInt("Choose a suit:\n1. Clubs\n2. Spades\n3. Hearts\n4. Diamonds ", 1,
                    4,
                    "Invalid");
        }
}
