package com.codecool.dungeoncrawl.model.actors;

import com.codecool.dungeoncrawl.controller.GameController;
import com.codecool.dungeoncrawl.display.cells.Cell;

import java.util.Arrays;
import java.util.List;

public class Rat extends Actor implements Mob {
    private int moveTimer = 0;
    private final int moveTimerLimit = 2;
    private final List<MovementDir> moves = Arrays.asList(MovementDir.M_UP, MovementDir.M_RIGHT, MovementDir.M_DOWN, MovementDir.M_LEFT);
    private MovementDir lastMove = moves.get((int) (Math.random() * 4));

    public Rat(Cell cell) {
        super(cell);
        this.attack = 2;
        health = 8;
    }

    @Override
    public MovementDir getPotentialMoveDirection(){
        if (moveTimer < moveTimerLimit) {
            moveTimer++;
        } else {
            moveTimer = 0;
            lastMove = GameController.getInstance().getActorController().getMoveSubcontroller().moveInCircles(moves, lastMove);
            return lastMove;
        }
        return MovementDir.M_NONE;
    }

    @Override
    public String getCellImageName() {
        return "rat";
    }

    @Override
    public int getAttack() {
        return attack;
    }


}
