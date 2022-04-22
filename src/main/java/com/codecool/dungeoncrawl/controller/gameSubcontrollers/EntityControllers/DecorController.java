package com.codecool.dungeoncrawl.controller.gameSubcontrollers.EntityControllers;

import com.codecool.dungeoncrawl.controller.GameController;
import com.codecool.dungeoncrawl.controller.gameSubcontrollers.EntityControllers.actorSubcontrollers.InteractionController;
import com.codecool.dungeoncrawl.display.cells.Cell;
import com.codecool.dungeoncrawl.model.actors.MovementDir;
import com.codecool.dungeoncrawl.model.actors.Player;
import com.codecool.dungeoncrawl.model.decor.CardPuzzle;
import com.codecool.dungeoncrawl.model.decor.CardType;
import com.codecool.dungeoncrawl.model.decor.Decor;
import com.codecool.dungeoncrawl.model.decor.DecorType;
import com.codecool.dungeoncrawl.model.dto.PuzzleResultDTO;

import java.util.ArrayList;

public class DecorController {
    private Decor[][] decorMatrix;
    private Player player;

    public void initDecorMatrix(int x, int y) {
        decorMatrix = new Decor[x][y];
    }

    public void addDecorToController(int x, int y, Decor decor) {
        decorMatrix[x][y] = decor;
    }

    public void tryToInteractWithDecor(Cell cell, MovementDir movementDir) {
        Decor decor = decorMatrix[cell.getX()][cell.getY()];
        DecorType decorType = decor.getDecorType();
        InteractionController interactionController = GameController.getInstance().getActorController().getInteractionController();
        switch (decorType) {
            case SPIDER_WEB -> interactionController.interactWithSpiderWeb(movementDir, player);
            case SHRINE -> interactionController.interactWithShrine(decor, player);
            case CARD_PUZZLE -> interactionController.interactWithCard((CardPuzzle) decor);
        }
    }

    public Decor[][] getDecorMatrix() {
        return decorMatrix;
    }

    public void setDecorMatrix(Decor[][] decorMatrix) {
        this.decorMatrix = decorMatrix;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public PuzzleResultDTO checkPuzzleSolved() {
        ArrayList<CardPuzzle> cardPuzzles = getCardPuzzleList();
        PuzzleResultDTO puzzleResultDTO = new PuzzleResultDTO(cardPuzzles);
        CardType firstCardType = null;
        for (CardPuzzle cardPuzzle : cardPuzzles) {
            if (firstCardType == null) {
                firstCardType = cardPuzzle.getCardType();
            } else {
                if (cardPuzzle.getCardType() == firstCardType) {
                    if (cardPuzzles.get(2).getCardType() == firstCardType) {
                        puzzleResultDTO.setSolved(true);
                        return puzzleResultDTO;
                    }
                }
                puzzleResultDTO.setSolved(false);
                return puzzleResultDTO;
            }
        }
        puzzleResultDTO.setSolved(false);
        return puzzleResultDTO;
    }

    private ArrayList<CardPuzzle> getCardPuzzleList() {
        ArrayList<CardPuzzle> cardPuzzles = new ArrayList<>();
        for (Decor[] decorRow : decorMatrix) {
            for (Decor decor : decorRow) {
                if (decor != null && decor.getDecorType().equals(DecorType.CARD_PUZZLE)) {
                    cardPuzzles.add((CardPuzzle) decor);
                }
            }
        }
        return cardPuzzles;
    }

    public void updateDecors() {
        for (Decor[] decors : decorMatrix)
            for (Decor decor : decors) {
                if (decor != null)
                    decor.updateCell();
            }
    }
}
