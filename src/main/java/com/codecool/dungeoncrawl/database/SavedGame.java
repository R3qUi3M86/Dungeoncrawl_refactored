package com.codecool.dungeoncrawl.database;

import com.codecool.dungeoncrawl.model.actors.Actor;
import com.codecool.dungeoncrawl.model.actors.Player;
import com.codecool.dungeoncrawl.model.decor.Decor;
import com.codecool.dungeoncrawl.model.items.Item;
import com.codecool.dungeoncrawl.model.map.GameMap;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SavedGame {
    private Player player;
    private Actor[][] actorMatrix;
    private Item[][] itemMatrix;
    private Decor[][] decorMatrix;
    private GameMap gameMap;

    public SavedGame(Player player, Actor[][] actorMatrix, Item[][] itemMatrix, Decor[][] decorMatrix, GameMap gameMap){
        this.player = player;
        this.actorMatrix = actorMatrix;
        this.itemMatrix = itemMatrix;
        this.decorMatrix = decorMatrix;
        this.gameMap = gameMap;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Actor[][] getActorMatrix() {
        return actorMatrix;
    }

    public void setActorMatrix(Actor[][] actorMatrix) {
        this.actorMatrix = actorMatrix;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public Item[][] getItemMatrix() {
        return itemMatrix;
    }

    public void setItemMatrix(Item[][] itemMatrix) {
        this.itemMatrix = itemMatrix;
    }

    public Decor[][] getDecorMatrix() {
        return decorMatrix;
    }

    public void setDecorMatrix(Decor[][] decorMatrix) {
        this.decorMatrix = decorMatrix;
    }

    // getters and setters
}
