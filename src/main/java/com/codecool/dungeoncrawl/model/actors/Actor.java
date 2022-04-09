package com.codecool.dungeoncrawl.model.actors;

import com.codecool.dungeoncrawl.display.cells.Cell;
import com.codecool.dungeoncrawl.display.Drawable;

public abstract class Actor implements Drawable {
    private Cell cell;
    int health;
    int attack;
    boolean minion = false;
    private boolean alive = true;

    public Actor(Cell cell) {
        this.cell = cell;
    }

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
    }

    public void resolveEffects(){}

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isMinion() {
        return minion;
    }

    public void setMinion(boolean minion) {
        this.minion = minion;
    }
}
