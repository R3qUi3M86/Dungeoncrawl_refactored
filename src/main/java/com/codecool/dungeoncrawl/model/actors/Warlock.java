package com.codecool.dungeoncrawl.model.actors;

import com.codecool.dungeoncrawl.Util;
import com.codecool.dungeoncrawl.controller.GameController;
import com.codecool.dungeoncrawl.display.cells.Cell;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Warlock extends Actor implements Mob, Summoner{
    private int moveTimer = 0;
    private final int moveTimerLimit = 0;
    private final List<MovementDir> moves = Arrays.asList(MovementDir.M_UP, MovementDir.M_RIGHT, MovementDir.M_DOWN, MovementDir.M_LEFT);

    private final int minionLimit = 2;
    private int spawnMinionTimer = 0;
    private final int spawnMinionTimerLimit = 6;
    private final ArrayList<Minion> minions = new ArrayList<>();

    public Warlock(Cell cell) {
        super(cell);
        this.attack = 3;
        health = 15;
    }

    @Override
    public void resolveEffects() {
        spawnMinion();
    }

    @Override
    public void spawnMinion() {
        if (spawnMinionTimer < spawnMinionTimerLimit) {
            spawnMinionTimer++;
        } else {
            if (minions.size() < minionLimit) {
                ArrayList<Cell> validAdjacentCells = Util.getValidAdjacentCells(getCell());
                if (validAdjacentCells.size() > 0) {
                    Cell cell = validAdjacentCells.get((int) (Math.random() * validAdjacentCells.size()));
                    Skeleton skeleton = new Skeleton(cell, this);
                    minions.add(skeleton);
                    GameController.getInstance().getActorController().addNpcToController(skeleton.getX(), skeleton.getY(), skeleton);
                }
            }
            spawnMinionTimer = 0;
        }
    }

    @Override
    public ArrayList<Minion> getMinions() {
        return minions;
    }

    @Override
    public MovementDir getPotentialMoveDirection() {
        if (moveTimer < moveTimerLimit) {
            moveTimer++;
        } else {
            moveTimer = 0;
            return GameController.getInstance().getActorController().getMoveSubcontroller().moveInRandomDirection(moves);
        }
        return MovementDir.M_NONE;
    }

    @Override
    public String getCellImageName() {
        return "warlock";
    }

    @Override
    public int getAttack() {
        return attack;
    }
}
