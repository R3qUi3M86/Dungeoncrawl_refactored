package com.codecool.dungeoncrawl.model.items;

import com.codecool.dungeoncrawl.model.Cell;

public class Key extends Item{

    public Key(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "key";
    }

}
