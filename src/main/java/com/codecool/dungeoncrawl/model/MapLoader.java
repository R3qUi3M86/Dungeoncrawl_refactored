package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.model.actors.Rat;
import com.codecool.dungeoncrawl.model.actors.Skeleton;
import com.codecool.dungeoncrawl.model.actors.Warlock;
import com.codecool.dungeoncrawl.model.decor.SpiderWeb;
import com.codecool.dungeoncrawl.model.items.Booze;
import com.codecool.dungeoncrawl.model.items.Key;
import com.codecool.dungeoncrawl.model.items.Meat;
import com.codecool.dungeoncrawl.model.items.Sword;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    private static final String[] mapNames = new String[]{"/map1.txt", "/map2.txt", "/map3.txt", "/map4.txt", "/map5.txt"};
    private static int width;
    private static int height;

    public static GameMap loadMap(int mapNumber) {
        InputStream is = MapLoader.class.getResourceAsStream(mapNames[mapNumber]);
        Scanner scanner = new Scanner(is);
        width = scanner.nextInt();
        height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case 'D':
                            cell.setType(CellType.CLOSED_DOOR);
                            break;
                        case 'd':
                            cell.setType(CellType.OPENED_DOOR);
                            break;
                        case '^':
                            cell.setType(CellType.STAIRS_UP);
                            break;
                        case 'v':
                            cell.setType(CellType.STAIRS_DOWN);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 'z':
                            cell.setType(CellType.FLOOR); // decor celltype
                            new SpiderWeb(cell);
                            break;
                        case 'E':
                            cell.setType(CellType.FLOOR);
                            cell.setExit(true);
                            break;
                        case 'r':
                            cell.setType(CellType.FLOOR);
                            Rat rat = new Rat(cell);
                            map.addActor(rat);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            Skeleton skeleton = new Skeleton(cell);
                            map.addActor(skeleton);
                            break;
                        case 'o':
                            cell.setType(CellType.FLOOR);
                            Warlock warlock = new Warlock(cell);
                            map.addActor(warlock);
                            break;
                        case 'k':
                            cell.setType(CellType.FLOOR);
                            new Key(cell);
                            break;
                        case 'm':
                            cell.setType(CellType.FLOOR);
                            new Meat(cell);
                            break;
                        case 'b':
                            cell.setType(CellType.FLOOR);
                            new Booze(cell);
                            break;
                        case 'w':
                            cell.setType(CellType.FLOOR);
                            new Sword(cell);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayerStartingPosition(new int[]{x, y});
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }
}
