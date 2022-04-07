package com.codecool.dungeoncrawl.model.actors;

import com.codecool.dungeoncrawl.Util;
import com.codecool.dungeoncrawl.display.cells.Cell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Warlock extends Actor {
    private int moveTimer = 0;
    private final int moveTimerLimit = 0;
    private final ArrayList<Actor> minions = new ArrayList<>();
    private int spawnMinionTimer = 0;
    private final int spawnMinionTimerLimit = 6;
    private final int minionLimit = 2;
    private final List<MovementDir> moves = Arrays.asList(MovementDir.M_UP, MovementDir.M_RIGHT, MovementDir.M_DOWN, MovementDir.M_LEFT);

    public Warlock(Cell cell) {
        super(cell);
        this.attack = 3;
        health = 15;
    }

    @Override
    public void moveActor() {
        spawnMinion();
        if (moveTimer < moveTimerLimit) {
            moveTimer++;
        } else {
            MovementDir monsterMovement = moves.get((int) (Math.random() * 4));
            move(monsterMovement.getDx(), monsterMovement.getDy());
            moveTimer = 0;
        }
    }

    @Override
    public String getCellImageName() {
        return "warlock";
    }

    @Override
    public int getAttack() {
        return attack;
    }

    public void spawnMinion() {
        if (spawnMinionTimer < spawnMinionTimerLimit) {
            spawnMinionTimer++;
        } else {
            if (minions.size() < minionLimit) {
                ArrayList<Cell> validAdjacentCells = Util.getValidAdjacentCells(getCell());
                if (validAdjacentCells.size() > 0) {
                    Cell cell = validAdjacentCells.get((int) (Math.random() * validAdjacentCells.size()));
                    Skeleton skeleton = new Skeleton(cell, this);
                    cell.setActor(skeleton);
                    minions.add(skeleton);
                }
            }
            spawnMinionTimer = 0;
        }
    }

    public ArrayList<Actor> getMinions() {
        return minions;
    }
}
