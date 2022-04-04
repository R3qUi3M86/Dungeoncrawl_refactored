package com.codecool.dungeoncrawl.model.actors;

import com.codecool.dungeoncrawl.model.Cell;

import java.util.Arrays;
import java.util.List;

public class Skeleton extends Actor {
    private int moveTimer = 0;
    private final int moveTimerLimit = 1;
    private final List<MonsterMovement> moves = Arrays.asList(MonsterMovement.M_UP, MonsterMovement.M_RIGHT, MonsterMovement.M_DOWN, MonsterMovement.M_LEFT);
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
            MonsterMovement monsterMovement = moves.get((int) (Math.random() * 4));
            move(monsterMovement.getDx(), monsterMovement.getDy());
            moveTimer = 0;
        }
    }

    @Override
    public String getTileName() {
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
