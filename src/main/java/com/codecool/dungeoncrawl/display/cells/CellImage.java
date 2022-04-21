package com.codecool.dungeoncrawl.display.cells;

import java.io.Serializable;

public enum CellImage implements Serializable {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    TREE1("tree1"),
    TREE2("tree2"),
    TREE3("tree3"),
    TREE4("tree4"),
    TREE5("tree5"),
    TREE6("tree6"),
    TREE7("tree7"),
    TREE8("tree8"),
    WEED1("weed1"),
    WEED2("weed2"),
    WEED3("weed3"),
    WEED4("weed4"),
    SIGN_POST("sign post"),
    CLOSED_DOOR("closed door"),
    OPENED_DOOR("opened door"),
    STAIRS_UP("stairs up"),
    STAIRS_DOWN("stairs down"),
    DECORATION("decoration");

    private final String tileName;

    CellImage(String tileName) {
        this.tileName = tileName;
    }

    public String getName() {
        return tileName;
    }
}
