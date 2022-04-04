package com.codecool.dungeoncrawl.model.actors;

import com.codecool.dungeoncrawl.model.Cell;
import com.codecool.dungeoncrawl.model.CellType;
import com.codecool.dungeoncrawl.model.Drawable;
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
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        if (!isActorOutOfMap(dx, dy)){
            Cell nextCell = cell.getNeighboringCell(dx, dy);
            if (nextCell.getType() == CellType.WALL) {
                return;
            } else if (nextCell.getActor() != null) {
                combat(nextCell);
                return;
            } else if (nextCell.getType() == CellType.CLOSED_DOOR) {
                if (this.getTileName().equals("player")) { // override in player
                    Player player = (Player) this;
                    Backpack backpack = player.getBackpack();
                    for (BackpackCell backpackCell : backpack.getBackpackItems().keySet()) {
                        Item item = backpack.getBackpackItems().get(backpackCell);
                        if (Objects.equals(item.getTileName(), "key")) {
                            nextCell.setType(CellType.OPENED_DOOR);
                            backpack.getBackpackItems().put(backpackCell, new EmptySpace());
                            return;
                        }
                    }
                }
                return;
            }
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    private void combat(Cell nextCell) {
        if (!this.getTileName().equals("player") && !nextCell.getActor().getTileName().equals("player"))
            return;
        nextCell.getActor().takeDamage(getAttack());
        if (nextCell.getActor().isAlive()){
            takeDamage(nextCell.getActor().getAttack());
            if (!alive){
                cell.setActor(null);
            }
        } else {
            nextCell.setActor(null);
        }
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
        health -= damage;
        if (health <= 0) {
            killActor();
        }
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

    public boolean isActorOutOfMap(int dx, int dy){
        int mapWidth = cell.getGameMap().getWidth();
        int mapHeight = cell.getGameMap().getHeight();
        if ((dx == 1) && (this.getX() + dx == mapWidth))
            return true;
        else if ((dx == -1) && (this.getX() + dx == -1))
            return true;
        else if ((dy == 1) && (this.getY() + dy == mapHeight))
            return true;
        else return (dx == -1) && (this.getY() + dy == -1);
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
