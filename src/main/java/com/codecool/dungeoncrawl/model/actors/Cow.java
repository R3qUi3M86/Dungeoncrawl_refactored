package com.codecool.dungeoncrawl.model.actors;

import com.codecool.dungeoncrawl.controller.GameController;
import com.codecool.dungeoncrawl.display.cells.Cell;
import com.codecool.dungeoncrawl.model.items.Meat;

import java.util.Arrays;
import java.util.List;

public class Cow extends Actor implements Mob {
    private int moveTimer = 0;
    private final int moveTimerLimit = 25;
    private final List<MovementDir> moves = Arrays.asList(MovementDir.M_RIGHT, MovementDir.M_LEFT);
    private MovementDir lastMove = moves.get((int) (Math.random() * 2));

    public Cow(Cell cell) {
        super(cell);
        this.attack = 0;
        health = 25;
    }

    @Override
    public MovementDir getPotentialMoveDirection(){
        if (moveTimer < moveTimerLimit) {
            moveTimer++;
        } else {
            moveTimer = 0;
            lastMove = GameController.getInstance().getActorController().getMoveSubcontroller().moveInSequence(moves, lastMove);
            return lastMove;
        }
        return MovementDir.M_NONE;
    }

    @Override
    public String getCellImageName() {
        return "cow";
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    void killActor() {
        super.killActor();
        GameController.getInstance().getItemController().getItemMatrix()[this.getX()][this.getY()] = new Meat(this.getCell());
    }

}
