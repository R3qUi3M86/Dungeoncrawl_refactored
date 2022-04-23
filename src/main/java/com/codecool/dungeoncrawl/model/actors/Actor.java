package com.codecool.dungeoncrawl.model.actors;

import com.codecool.dungeoncrawl.display.cells.Cell;
import com.codecool.dungeoncrawl.display.Drawable;

import java.io.Serializable;

public abstract class Actor implements Drawable, Serializable {
    private Cell cell;
    int health;
    int attack;
    int armor = 0;
    boolean minion = false;
    private boolean alive = true;

    public Actor(Cell cell) {
        this.cell = cell;
    }

    public void resolveEffects(){}

    public void takeDamage(int damage){
        if (health <= damage) {
            killActor();
        }
        health -= damage;
    }

    void killActor() {
        alive = false;
    }

    public int getHealth() {
        return health;
    }

    public int getArmor() {
        return armor;
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

    public boolean isAlive() {
        return alive;
    }

    public boolean isMinion() {
        return minion;
    }

    abstract public int getAttack();

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setCell(Cell cell){
        this.cell = cell;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
