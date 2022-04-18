package com.codecool.dungeoncrawl.model.decor;

import com.codecool.dungeoncrawl.display.cells.Cell;

import java.util.ArrayList;

public class CardPuzzle extends Decor {
    CardType[] cardSequence;
    private int cardNumber;
    private int puzzleState;
    private ArrayList<CardPuzzle> otherCards = new ArrayList<>();

    public CardPuzzle(Cell cell, int cardNumber) {
        super(cell);
        this.cardNumber = cardNumber;
        setCardSequence(cardNumber);
        this.puzzleState = 0;
    }

    private void setCardSequence(int cardNumber) {
        switch (cardNumber) {
            case 0 -> this.cardSequence = new CardType[]{CardType.HEARTS, CardType.DIAMONDS, CardType.CLUBS};
            case 1 -> this.cardSequence = new CardType[]{CardType.SPADES, CardType.DIAMONDS, CardType.HEARTS};
            case 2 -> this.cardSequence = new CardType[]{CardType.CLUBS, CardType.HEARTS, CardType.SPADES};
        }
    }

    @Override
    public String getCellImageName() {
        return cardSequence[puzzleState].toString();
    }

    public CardType getCardType() {
        return cardSequence[puzzleState];
    }

    @Override
    public DecorType getDecorType() {
        return DecorType.CARD_PUZZLE;
    }

    public void switchPuzzleState() {
        puzzleState += 1;
        if (puzzleState > 2)
            puzzleState = 0;
    }

    public void setOtherCards(ArrayList<CardPuzzle> otherCards) {
        this.otherCards = otherCards;
    }
}
