package com.codecool.dungeoncrawl.model.decor;

import com.codecool.dungeoncrawl.model.Cell;

public class SpiderWeb extends Decor {

    public SpiderWeb(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "spiderweb";
    }

}
