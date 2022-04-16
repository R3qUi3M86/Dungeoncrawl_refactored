package com.codecool.dungeoncrawl.display.tiles;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> dungeonTileMap = new HashMap<>();
    private static Map<String, Tile> forestTileMap = new HashMap<>();

    static {
        createDefaultTiles(dungeonTileMap);
        dungeonTileMap.put("wall", new Tile(10, 17));
        dungeonTileMap.put("floor", new Tile(2, 0));
        dungeonTileMap.put("opened golden door", new Tile(12, 11));
        dungeonTileMap.put("closed golden door", new Tile(13, 11));
        dungeonTileMap.put("opened blue door", new Tile(2, 9));
        dungeonTileMap.put("closed blue door", new Tile(0, 9));
        dungeonTileMap.put("opened red door", new Tile(8, 10));
        dungeonTileMap.put("closed red door", new Tile(6, 10));
        dungeonTileMap.put("stairs up", new Tile(2, 6));
        dungeonTileMap.put("stairs down", new Tile(3, 6));

        createDefaultTiles(forestTileMap);
        forestTileMap.put("wall", new Tile(10, 17));
        forestTileMap.put("floor", new Tile(2, 0));
        forestTileMap.put("opened golden door", new Tile(12, 11));
        forestTileMap.put("closed golden door", new Tile(13, 11));
        forestTileMap.put("opened blue door", new Tile(2, 9));
        forestTileMap.put("closed blue door", new Tile(0, 9));
        forestTileMap.put("opened red door", new Tile(8, 10));
        forestTileMap.put("closed red door", new Tile(6, 10));
        forestTileMap.put("stairs up", new Tile(2, 6));
        forestTileMap.put("stairs down", new Tile(3, 6));

    }

    private static void createDefaultTiles(Map<String, Tile> map){
        map.put("empty", new Tile(0, 0));
        map.put("spiderweb", new Tile(2, 15));
        map.put("shrine", new Tile(1, 14));
        map.put("player", new Tile(27, 0));
        map.put("rat", new Tile(31, 8));
        map.put("skeleton", new Tile(29, 6));
        map.put("warlock", new Tile(24, 1));
        map.put("demon", new Tile(27, 2));
        map.put("golden key", new Tile(16, 23));
        map.put("blue key", new Tile(17, 23));
        map.put("red key", new Tile(18, 23));
        map.put("sword", new Tile(2, 28));
        map.put("meat", new Tile(16, 28));
        map.put("booze", new Tile(16, 30));
    }


    public static Map<String, Tile> getDungeonTileMap() {
        return dungeonTileMap;
    }

    public static Map<String, Tile> getForestTileMap() {
        return forestTileMap;
    }

    public static Map<String, Tile> getHouseTileMap() {
        return dungeonTileMap;
    }
    public static Image getTileset() {
        return tileset;
    }
}
