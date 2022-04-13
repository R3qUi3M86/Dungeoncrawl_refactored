package com.codecool.dungeoncrawl.controller.gameSubcontrollers.EntityControllers;

import com.codecool.dungeoncrawl.controller.GameController;
import com.codecool.dungeoncrawl.controller.gameSubcontrollers.EntityControllers.actorSubcontrollers.InteractionSubcontroller;
import com.codecool.dungeoncrawl.display.cells.Cell;
import com.codecool.dungeoncrawl.model.actors.MovementDir;
import com.codecool.dungeoncrawl.model.actors.Player;
import com.codecool.dungeoncrawl.model.decor.Decor;
import com.codecool.dungeoncrawl.model.decor.DecorType;

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
        }
    }

    public Decor[][] getDecorMatrix() {
        return decorMatrix;
    }

    public void setPlayer(Player player){
        this.player = player;
    }
}
