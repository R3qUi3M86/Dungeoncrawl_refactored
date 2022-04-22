package com.codecool.dungeoncrawl.model.dto;

import com.codecool.dungeoncrawl.model.decor.CardPuzzle;

import java.util.ArrayList;

public class PuzzleResultDTO {
    private boolean solved;
    private ArrayList<CardPuzzle> cardPuzzles;

    public PuzzleResultDTO(ArrayList<CardPuzzle> cardPuzzles) {
        this.cardPuzzles = cardPuzzles;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public boolean isSolved() {
        return solved;
    }

    public ArrayList<CardPuzzle> getCardPuzzles() {
        return cardPuzzles;
    }
}
