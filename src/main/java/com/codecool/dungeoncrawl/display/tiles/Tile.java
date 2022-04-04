package com.codecool.dungeoncrawl.display.tiles;

public class Tile {
    public static int TILE_WIDTH = 32;
    public final int x, y, w, h;

    Tile(int i, int j) {
        x = i * (TILE_WIDTH + 2);
        y = j * (TILE_WIDTH + 2);
        w = TILE_WIDTH;
        h = TILE_WIDTH;
    }
}
