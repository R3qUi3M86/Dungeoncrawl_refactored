package com.codecool.dungeoncrawl.model.actors;

import com.codecool.dungeoncrawl.controller.GameController;
import com.codecool.dungeoncrawl.display.cells.Cell;
import com.codecool.dungeoncrawl.model.items.Armor;
import com.codecool.dungeoncrawl.model.items.Item;
import com.codecool.dungeoncrawl.model.items.Sword;
import com.codecool.dungeoncrawl.model.items.Weapon;
import com.codecool.dungeoncrawl.model.items.backpack.Backpack;
import com.codecool.dungeoncrawl.model.items.backpack.BackpackCell;
import com.codecool.dungeoncrawl.model.items.backpack.SmallBackpack;

import java.io.Serializable;
import java.util.Objects;

public class Player extends Actor {
    private int maxHealth = 20; // put values into constructor
    Backpack backpack = new SmallBackpack();
    private int drunkCounter = 0;
    private int drunkLimit = 30;
    private boolean wasted = false;
    private boolean slowed = false;

    public Player(Cell cell) {
        super(cell);
        this.attack = 5;
        this.health = 20;
    }

    public void eatFood(int heal) {
        if (health + heal <= maxHealth)
            health += heal;
        else health = maxHealth;
    }

    @Override
    public void takeDamage(int damage){
        if (!wasted) {
            super.takeDamage(damage);
        }
    }

    @Override
    public void resolveEffects(){
        if (wasted){
            drunkCounter++;
            if (drunkCounter == drunkLimit) {
                wasted = false;
                GameController.getInstance().getUserInputController().setSoberKeyMapping();
            }
        }
    }

    @Override
    public int getAttack(){
        for ( BackpackCell backpackCell : backpack.getBackpackItems().keySet() ) {
            Item item = backpack.getBackpackItems().get(backpackCell);
            if (item instanceof Weapon) {
                return attack + ((Weapon) item).getDamageModifier();
            }
        }
        return attack;
    }

    @Override
    public int getArmor() {
        return super.getArmor() + getArmorFromItems();
    }

    private int getArmorFromItems(){
        int armor = 0;
        for( BackpackCell backpackCell : backpack.getBackpackItems().keySet()){
            Item item = backpack.getBackpackItems().get(backpackCell);
            if (item instanceof Armor){
                armor += ((Armor) item).getArmorValue();
            }
        }
        return armor;
    }

    public String getCellImageName() {
        return "player";
    }

    public Backpack getBackpack() {
        return backpack;
    }

    public void getWasted() {
        wasted = true;
    }

    public boolean isSlowed() {
        return slowed;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setSlowed(boolean slowed) {
        this.slowed = slowed;
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
