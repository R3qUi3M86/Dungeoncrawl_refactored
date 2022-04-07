package com.codecool.dungeoncrawl.model.actors;

import com.codecool.dungeoncrawl.display.cells.Cell;
import com.codecool.dungeoncrawl.display.cells.CellType;
import com.codecool.dungeoncrawl.display.Drawable;
import com.codecool.dungeoncrawl.model.items.Item;
import com.codecool.dungeoncrawl.model.items.backpack.Backpack;
import com.codecool.dungeoncrawl.model.items.backpack.BackpackCell;
import com.codecool.dungeoncrawl.model.items.backpack.EmptySpace;

import java.util.Objects;

public abstract class Actor implements Drawable {
    private Cell cell;
    int health;
    int attack;
    private boolean alive = true;

    public Actor(){}

    public Actor(Cell cell) {
        this.cell = cell;
    }

    abstract public void moveActor(); // update turn

    public int getHealth() {
        return health;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public void takeDamage(int damage){
        if (health <= damage) {
            killActor();
        }
        health -= damage;
    }

    void killActor() {
        alive = false;
    }

    abstract public int getAttack();

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setCell(Cell cell){
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
