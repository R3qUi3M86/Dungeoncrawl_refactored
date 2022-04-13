package com.codecool.dungeoncrawl.model.decor;

import com.codecool.dungeoncrawl.display.cells.Cell;

public class Shrine extends Decor{

    public Shrine(Cell cell) {
        super(cell);
    }

    @Override
    public String getCellImageName() {
        return DecorType.SHRINE.toString();
    }

    @Override
    public DecorType getDecorType() {
        return DecorType.SHRINE;
    }
}
