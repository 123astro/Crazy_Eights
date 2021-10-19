package com.company.actor;

import com.company.crazyeights.Actor;
import com.company.deck.Card;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AutoPlayer implements Actor {
    private boolean canPlay = true;
    private final String name;

    public AutoPlayer(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int chooseCard(List<Card> cards, Card activeCard) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getSuit().equals(activeCard.getSuit()) ||
                    cards.get(i).getRank() == activeCard.getRank())
                return i + 1;
        }
        canPlay = false;
        return 1;
    }

    @Override
    public int getAction() { // 1 = draw , 2 = play a card
        if(!canPlay){
            canPlay = true;
            return 1;
        }
        return 2 ;
    }

    @Override
    public int setSuit(List<Card> cards) {
        //int suit = (int) ((Math.random() * 4) + 1);
        Map<String, Integer> suitFreq = new HashMap<>();
        for (int i = 0; i < cards.size(); i++)
        {
            if (suitFreq.containsKey(cards.get(i).getSuit()))
            {
                int value = suitFreq.get(cards.get(i).getSuit());
                value = value + 1;
                suitFreq.put(cards.get(i).getSuit(), value);
            } else
            {
               suitFreq.put(cards.get(i).getSuit(), 1);
            }
        }
        String newSuit = null;
        int max = Integer.MIN_VALUE;
        for(String key : suitFreq.keySet()) {
            if( suitFreq.get(key) > max ){
                max = suitFreq.get(key);
                newSuit = key;
            }
        }
        assert newSuit != null;
        int suit = switch (newSuit) {
            case "\u2667" ->  1;
            case "\u2664" ->  2;
            case "\u2665" ->  3;
            case "\u2666" ->  4;
            default -> -1;
        };

       return suit;
    }

}
