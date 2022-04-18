package com.codecool.dungeoncrawl.controller.gameSubcontrollers.EntityControllers;

import com.codecool.dungeoncrawl.controller.GameController;
import com.codecool.dungeoncrawl.controller.gameSubcontrollers.EntityControllers.actorSubcontrollers.InteractionSubcontroller;
import com.codecool.dungeoncrawl.display.cells.Cell;
import com.codecool.dungeoncrawl.model.actors.MovementDir;
import com.codecool.dungeoncrawl.model.actors.Player;
import com.codecool.dungeoncrawl.model.decor.CardPuzzle;
import com.codecool.dungeoncrawl.model.decor.CardType;
import com.codecool.dungeoncrawl.model.decor.Decor;
import com.codecool.dungeoncrawl.model.decor.DecorType;

import java.util.ArrayList;
import java.util.Arrays;

public class DecorController {
    private Decor[][] decorMatrix;
    private Player player;

    public void initDecorMatrix(int x, int y){
        decorMatrix = new Decor[x][y];
    }

    public void addDecorToController(int x, int y, Decor decor){
        decorMatrix[x][y] = decor;
    }

    public void tryToInteractWithDecor(Cell cell, MovementDir movementDir){
        Decor decor = decorMatrix[cell.getX()][cell.getY()];
        DecorType decorType = decor.getDecorType();
        InteractionSubcontroller interactionSubcontroller = GameController.getInstance().getActorController().getInteractionSubcontroller();
        switch (decorType){
            case SPIDER_WEB -> interactionSubcontroller.interactWithSpiderWeb(movementDir, player);
            case SHRINE -> interactionSubcontroller.interactWithShrine(decor, player);
            case CARD_PUZZLE -> interactionSubcontroller.interactWithCard((CardPuzzle) decor);
        }
    }

    public Decor[][] getDecorMatrix() {
        return decorMatrix;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public boolean checkPuzzleSolved(){
        ArrayList<CardPuzzle> cardPuzzles = new ArrayList<>();
        for (Decor[] decorRow : decorMatrix){
            System.out.println(Arrays.deepToString(decorMatrix));
            for (Decor decor : decorRow){
                if (decor.getDecorType().equals(DecorType.CARD_PUZZLE)){
                    cardPuzzles.add((CardPuzzle) decor);
                }
            }
        }
        CardType firstCardType = null;
        for (CardPuzzle cardPuzzle : cardPuzzles){
            if (firstCardType == null){
                firstCardType = cardPuzzle.getCardType();
            } else {
                if (cardPuzzle.getCardType() == firstCardType){
                    return cardPuzzles.get(2).getCardType() == firstCardType;
                } else {
                    return false;
                }

            }
        }
        return false;
    }
}
