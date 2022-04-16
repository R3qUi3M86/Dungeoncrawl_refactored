package com.codecool.dungeoncrawl.display.cells;

public enum CellImage {
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
