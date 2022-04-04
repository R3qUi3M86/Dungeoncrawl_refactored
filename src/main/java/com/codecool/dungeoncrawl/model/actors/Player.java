package com.codecool.dungeoncrawl.model.actors;

import com.codecool.dungeoncrawl.model.Cell;
import com.codecool.dungeoncrawl.model.items.Item;
import com.codecool.dungeoncrawl.model.items.Sword;
import com.codecool.dungeoncrawl.model.items.backpack.Backpack;
import com.codecool.dungeoncrawl.model.items.backpack.BackpackCell;
import com.codecool.dungeoncrawl.model.items.backpack.SmallBackpack;

import java.util.Objects;

public class Player extends Actor {
    private int maxHealth = 15; // put values into constructor
    Backpack backpack = new SmallBackpack();
    private int drunkCounter = 0;
    private int drunkLimit = 20;
    private boolean wasted = false;

    public Player(Cell cell) {
        super(cell);
        this.attack = 5;
        this.health = 15;
    }

    @Override
    public void moveActor() {}

    public String getTileName() {
        return "player";
    }

    public Backpack getBackpack() {
        return backpack;
    }

    @Override
    public int getAttack(){
        for ( BackpackCell backpackCell : backpack.getBackpackItems().keySet() ) {
            Item item = backpack.getBackpackItems().get(backpackCell);
            if (Objects.equals(item.getTileName(), "sword")) { // instanceof interface weapon
                Sword sword = (Sword) item;
                return attack + sword.getDamageModifier();
            }
        }
        return attack;
    }

    public void eatFood(int heal) {
        if (health + heal <= maxHealth)
            health += heal;
        else health = maxHealth;
    }

    public void getWasted() {
        wasted = true;
    }

    @Override
    public void takeDamage(int damage){
        if (!wasted) {
            health -= damage;
            if (health <= 0) {
                killActor();
            }
        }
    }

    public void resolveEffects(){
        if (wasted){
            drunkCounter++;
            if (drunkCounter == drunkLimit)
                wasted = false;
        }
    }

    public boolean isWasted() {
        return wasted;
    }

    public void setDrinkLimit(int drinkLimit) {
        this.drunkLimit = drinkLimit;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
}
