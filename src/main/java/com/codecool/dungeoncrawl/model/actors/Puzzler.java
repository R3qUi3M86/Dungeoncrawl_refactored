package com.codecool.dungeoncrawl.model.actors;
import com.codecool.dungeoncrawl.display.cells.Cell;

public class Puzzler extends Actor implements Mob{
    public Puzzler(Cell cell) {
        super(cell);
        health = 1;
    }

    @Override
    public String getCellImageName() {
        return "puzzler";
    }

    @Override
    public int getAttack() {
        return 0;
    }

    @Override
    public MovementDir getPotentialMoveDirection() {
        return MovementDir.M_NONE;
    }

    @Override
    public void takeDamage(int damage) {
    }
}
