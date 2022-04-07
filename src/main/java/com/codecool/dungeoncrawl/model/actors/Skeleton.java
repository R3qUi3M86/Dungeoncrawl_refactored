package com.codecool.dungeoncrawl.model.actors;

import com.codecool.dungeoncrawl.display.cells.Cell;

import java.util.Arrays;
import java.util.List;

public class Skeleton extends Actor {
    private int moveTimer = 0;
    private final int moveTimerLimit = 1;
    private final List<MovementDir> moves = Arrays.asList(MovementDir.M_UP, MovementDir.M_RIGHT, MovementDir.M_DOWN, MovementDir.M_LEFT);
    private Actor master;

    public Skeleton(Cell cell) {
        super(cell);
        this.attack = 3;
        health = 10;
    }

    public Skeleton(Cell cell, Actor master) {
        super(cell);
        this.attack = 3;
        this.master = master;
    }

    @Override
    public void moveActor() {
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
        return "skeleton";
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    void killActor() {
        super.killActor();
        if (master != null){
            Warlock warlock = (Warlock) master;
            warlock.getMinions().remove(this);
        }
    }

    public Actor getMaster() {
        return master;
    }
}
