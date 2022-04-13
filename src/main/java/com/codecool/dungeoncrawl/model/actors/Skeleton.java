package com.codecool.dungeoncrawl.model.actors;

import com.codecool.dungeoncrawl.controller.GameController;
import com.codecool.dungeoncrawl.display.cells.Cell;
import java.util.Arrays;
import java.util.List;

public class Skeleton extends Actor implements Mob, Minion{
    private int moveTimer = 0;
    private final int moveTimerLimit = 1;
    private final List<MovementDir> moves = Arrays.asList(MovementDir.M_UP, MovementDir.M_RIGHT, MovementDir.M_DOWN, MovementDir.M_LEFT);
    public Summoner master;

    public Skeleton(Cell cell) {
        super(cell);
        this.attack = 3;
        health = 10;
    }

    public Skeleton(Cell cell, Summoner master) {
        super(cell);
        this.minion = true;
        this.attack = 3;
        this.master = master;
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
        return "skeleton";
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public Summoner getMaster() {
        return master;
    }
}
