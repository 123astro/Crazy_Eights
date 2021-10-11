package com.company.deck;


public class Card {
    protected String suit;
    protected int rank;

    public Card(String suit, int rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public String getSuit(){return suit;}

    @Override
    public String toString() {
        return (rank + suit);
    }

    public String display() {
        String output = "";
        switch (rank) {
            case 1 -> output = "AC";
            case 11 -> output = "JA";
            case 12 -> output = "QU";
            case 13 -> output = "KI";
            default -> output = rank == 10 ? Integer.toString(rank) : " " + rank; // 10 is 2 digit
        }
        return output + suit;
    }
}

