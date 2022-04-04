package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.model.Cell;
import com.codecool.dungeoncrawl.model.CellType;
import com.codecool.dungeoncrawl.model.actors.Actor;
import com.codecool.dungeoncrawl.model.actors.Player;
import com.codecool.dungeoncrawl.model.actors.Warlock;

import java.util.ArrayList;


public class GameMap {
    private final int width;
    private final int height;
    private final Cell[][] cells;
    private int[] playerStartingPosition;
    private final ArrayList<Actor> npcs = new ArrayList<>();
    private Player player;

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void addActor(Actor actor) {
        npcs.add(actor);
    }

    public void removeActor(Actor actor) {
        npcs.remove(actor);
    }

    public void moveActors() {
        for (Actor actor : npcs) {
            if (actor.isAlive())
                actor.moveActor();
        }
    }

    public void setPlayerStartingPosition(int[] playerStartingPosition) {
        this.playerStartingPosition = playerStartingPosition;
    }

    public Cell getPlayerStartingCell(){
        return cells[playerStartingPosition[0]][playerStartingPosition[1]];
    }

    public void cleanUp() {
        ArrayList<Actor> npcsCopy = new ArrayList<>(npcs);
        for (Actor actor : npcsCopy) {
            if (!actor.isAlive())
                npcs.remove(actor);
            if (actor.getTileName().equals("warlock")){
                Warlock warlock = (Warlock) actor;
                ArrayList<Actor> minions = warlock.getMinions();
                for (Actor minion : minions){
                    if (!npcs.contains(minion))
                        npcs.add(minion);
                }
            }
        }
    }
}
