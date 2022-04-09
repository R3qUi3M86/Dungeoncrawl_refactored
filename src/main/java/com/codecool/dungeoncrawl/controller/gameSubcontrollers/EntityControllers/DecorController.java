package com.codecool.dungeoncrawl.controller.gameSubcontrollers.EntityControllers;

import com.codecool.dungeoncrawl.model.actors.Player;
import com.codecool.dungeoncrawl.model.decor.Decor;

public class DecorController {
    private Decor[][] decorMatrix;
    private Player player;

    public void initDecorMatrix(int x, int y){
        decorMatrix = new Decor[x][y];
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public void addDecorToController(int x, int y, Decor decor){
        decorMatrix[x][y] = decor;
    }

    public Decor[][] getDecorMatrix() {
        return decorMatrix;
    }
}
