package com.codecool.dungeoncrawl.model.actors;

import com.codecool.dungeoncrawl.display.cells.Cell;

import java.util.Arrays;
import java.util.List;

public class Rat extends Actor {
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
    public void moveActor() {
        if (moveTimer < moveTimerLimit) {
            moveTimer++;
        } else {
            int i = 0;
            for (MovementDir moveDir : moves) {
                if (lastMove == moveDir) {
                    if (i + 1 == moves.size()) {
                        move(moves.get(0).getDx(), moves.get(0).getDy());
                        lastMove = moves.get(0);
                    } else {
                        move(moves.get(i + 1).getDx(), moves.get(i + 1).getDy());
                        lastMove = moves.get(i + 1);
                    }
                    break;
                }
                i++;
            }
            moveTimer = 0;
        }
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
