package com.codecool.dungeoncrawl.display.cells;

import java.io.Serializable;

public enum CellType implements Serializable {
    ILLEGAL,
    WALKABLE,
    COLLISION,
    INTERACTION;
}
