package com.codecool.dungeoncrawl.model.actors;

import com.codecool.dungeoncrawl.Util;
import com.codecool.dungeoncrawl.model.Cell;

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
    private final List<MonsterMovement> moves = Arrays.asList(MonsterMovement.M_UP, MonsterMovement.M_RIGHT, MonsterMovement.M_DOWN, MonsterMovement.M_LEFT);

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
            MonsterMovement monsterMovement = moves.get((int) (Math.random() * 4));
            move(monsterMovement.getDx(), monsterMovement.getDy());
            moveTimer = 0;
        }
    }

    @Override
    public String getTileName() {
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
