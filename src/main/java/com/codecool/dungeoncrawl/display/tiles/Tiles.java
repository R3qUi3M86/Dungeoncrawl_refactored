package com.codecool.dungeoncrawl.display.tiles;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();

    static {
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("tree1", new Tile(0, 1));
        tileMap.put("tree2", new Tile(1, 1));
        tileMap.put("tree3", new Tile(2, 1));
        tileMap.put("tree4", new Tile(3, 1));
        tileMap.put("tree5", new Tile(4, 1));
        tileMap.put("tree6", new Tile(5, 1));
        tileMap.put("tree7", new Tile(3, 2));
        tileMap.put("tree8", new Tile(4, 2));
        tileMap.put("weed1", new Tile(5, 0));
        tileMap.put("weed2", new Tile(6, 0));
        tileMap.put("weed3", new Tile(7, 0));
        tileMap.put("weed4", new Tile(0, 2));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("opened golden door", new Tile(12, 11));
        tileMap.put("closed golden door", new Tile(13, 11));
        tileMap.put("opened blue door", new Tile(2, 9));
        tileMap.put("closed blue door", new Tile(0, 9));
        tileMap.put("opened red door", new Tile(8, 10));
        tileMap.put("closed red door", new Tile(6, 10));
        tileMap.put("stairs up", new Tile(2, 6));
        tileMap.put("stairs down", new Tile(3, 6));
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("spiderweb", new Tile(2, 15));
        tileMap.put("shrine", new Tile(1, 14));
        tileMap.put("player", new Tile(27, 0));
        tileMap.put("rat", new Tile(31, 8));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("warlock", new Tile(24, 1));
        tileMap.put("demon", new Tile(27, 2));
        tileMap.put("golden key", new Tile(16, 23));
        tileMap.put("blue key", new Tile(17, 23));
        tileMap.put("red key", new Tile(18, 23));
        tileMap.put("sword", new Tile(2, 28));
        tileMap.put("meat", new Tile(16, 28));
        tileMap.put("booze", new Tile(16, 30));
    }


    public static Map<String, Tile> getTileMap() {
        return tileMap;
    }

    public static Map<String, Tile> getHouseTileMap() {
        return tileMap;
    }
    public static Image getTileset() {
        return tileset;
    }
}
