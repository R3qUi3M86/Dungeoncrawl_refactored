package com.codecool.dungeoncrawl.model.decor;

import com.codecool.dungeoncrawl.display.cells.Cell;

public class SpiderWeb extends Decor {

    public SpiderWeb(Cell cell) {
        super(cell);
    }

    @Override
    public String getCellImageName() {
        return "spiderweb";
    }

}
