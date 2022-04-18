package com.codecool.dungeoncrawl.model.decor;

public enum CardType {
    HEARTS("ace of hearts"), DIAMONDS("ace of diamonds"), CLUBS("ace of clubs"), SPADES("ace of spades");

    private final String cardType;

    CardType(String cardType) {
        this.cardType = cardType;
    }

    @Override
    public String toString() {
        return cardType;
    }
}
