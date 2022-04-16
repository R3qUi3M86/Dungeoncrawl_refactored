package com.codecool.dungeoncrawl.model.map;

import com.codecool.dungeoncrawl.controller.GameController;
import com.codecool.dungeoncrawl.controller.gameSubcontrollers.EntityControllers.ActorController;
import com.codecool.dungeoncrawl.controller.gameSubcontrollers.EntityControllers.DecorController;
import com.codecool.dungeoncrawl.controller.gameSubcontrollers.EntityControllers.ItemController;
import com.codecool.dungeoncrawl.display.cells.*;
import com.codecool.dungeoncrawl.model.actors.Demon;
import com.codecool.dungeoncrawl.model.actors.Rat;
import com.codecool.dungeoncrawl.model.actors.Skeleton;
import com.codecool.dungeoncrawl.model.actors.Warlock;
import com.codecool.dungeoncrawl.model.decor.Shrine;
import com.codecool.dungeoncrawl.model.decor.SpiderWeb;
import com.codecool.dungeoncrawl.model.items.*;

import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

public class MapLoader {
    private static final String[] mapNames = new String[]{"/map1.txt", "/map2.txt", "/map3.txt", "/map4.txt", "/map5.txt"};
    private static int width;
    private static int height;

    public static GameMap loadMap(int mapNumber) {
        InputStream is = MapLoader.class.getResourceAsStream(mapNames[mapNumber]);
        Scanner scanner = new Scanner(is);
        CellRenderType cellRenderType = getMapCellRenderType(scanner);
        width = scanner.nextInt();
        height = scanner.nextInt();

        scanner.nextLine(); // empty line

        ActorController actorController = GameController.getInstance().getActorController();
        DecorController decorController = GameController.getInstance().getDecorController();
        ItemController itemController = GameController.getInstance().getItemController();

        actorController.initNPCsMatrix(width, height);
        itemController.initItemMatrix(width, height);
        decorController.initDecorMatrix(width, height);

        GameMap map = new GameMap(width, height, CellType.ILLEGAL, cellRenderType);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ' -> {
                            cell.setType(CellType.ILLEGAL);
                            cell.setImageType(CellImage.EMPTY);
                        }
                        case '#' -> {
                            cell.setType(CellType.COLLISION);
                            switch (cellRenderType){
                                case FOREST -> {cell.setForestImageType(CellImage.WALL);}
                                default -> {cell.setImageType(CellImage.WALL);}
                            }
                        }
                        case 'Z' -> {
                            cell.setType(CellType.INTERACTION);
                            cell.setImageType(CellImage.CLOSED_DOOR);
                            cell.setDoor(new Door(DoorType.GOLDEN_DOOR));
                        }
                        case 'N' -> {
                            cell.setType(CellType.INTERACTION);
                            cell.setImageType(CellImage.CLOSED_DOOR);
                            cell.setDoor(new Door(DoorType.BLUE_DOOR));
                        }
                        case 'C' -> {
                            cell.setType(CellType.INTERACTION);
                            cell.setImageType(CellImage.CLOSED_DOOR);
                            cell.setDoor(new Door(DoorType.RED_DOOR));
                        }
                        case 'z' -> {
                            cell.setType(CellType.WALKABLE);
                            cell.setImageType(CellImage.FLOOR);
                            itemController.addItemToController(x, y, new GoldenKey(cell));
                        }
                        case 'c' -> {
                            cell.setType(CellType.WALKABLE);
                            cell.setImageType(CellImage.FLOOR);
                            itemController.addItemToController(x, y, new RedKey(cell));
                        }
                        case 'n' -> {
                            cell.setType(CellType.WALKABLE);
                            cell.setImageType(CellImage.FLOOR);
                            itemController.addItemToController(x, y, new BlueKey(cell));
                        }
                        case '^' -> {
                            cell.setType(CellType.WALKABLE);
                            cell.setImageType(CellImage.STAIRS_UP);
                            cell.setExit(true);
                        }
                        case '>' -> {
                            cell.setType(CellType.WALKABLE);
                            cell.setImageType(CellImage.STAIRS_UP);
                        }
                        case 'v' -> {
                            cell.setType(CellType.WALKABLE);
                            cell.setImageType(CellImage.STAIRS_DOWN);
                            cell.setExit(true);
                        }
                        case '<' -> {
                            cell.setType(CellType.WALKABLE);
                            cell.setImageType(CellImage.STAIRS_DOWN);
                        }
                        case '.' -> {
                            cell.setType(CellType.WALKABLE);
                            cell.setImageType(CellImage.FLOOR);
                        }
                        case '*' -> {
                            cell.setType(CellType.INTERACTION);
                            cell.setImageType(CellImage.DECORATION);
                            decorController.addDecorToController(x, y, new SpiderWeb(cell));
                        }
                        case 'H' -> {
                            cell.setType(CellType.INTERACTION);
                            cell.setImageType(CellImage.DECORATION);
                            decorController.addDecorToController(x, y, new Shrine(cell));
                        }
                        case 'E' -> {
                            cell.setType(CellType.WALKABLE);
                            cell.setImageType(CellImage.FLOOR);
                            cell.setExit(true);
                        }
                        case 'r' -> {
                            cell.setType(CellType.WALKABLE);
                            cell.setImageType(CellImage.FLOOR);
                            actorController.addNpcToController(x, y, new Rat(cell));
                        }
                        case 's' -> {
                            cell.setType(CellType.WALKABLE);
                            cell.setImageType(CellImage.FLOOR);
                            actorController.addNpcToController(x, y, new Skeleton(cell));
                        }
                        case 'o' -> {
                            cell.setType(CellType.WALKABLE);
                            cell.setImageType(CellImage.FLOOR);
                            actorController.addNpcToController(x, y, new Warlock(cell));
                        }
                        case 'd' -> {
                            cell.setType(CellType.WALKABLE);
                            cell.setImageType(CellImage.FLOOR);
                            actorController.addNpcToController(x, y, new Demon(cell));
                        }
                        case 'm' -> {
                            cell.setType(CellType.WALKABLE);
                            cell.setImageType(CellImage.FLOOR);
                            itemController.addItemToController(x, y, new Meat(cell));
                        }
                        case '%' -> {
                            cell.setType(CellType.WALKABLE);
                            cell.setImageType(CellImage.FLOOR);
                            itemController.addItemToController(x, y, new Booze(cell));
                        }
                        case 'w' -> {
                            cell.setType(CellType.WALKABLE);
                            cell.setImageType(CellImage.FLOOR);
                            itemController.addItemToController(x, y, new Sword(cell));
                        }
                        case '@' -> {
                            cell.setType(CellType.WALKABLE);
                            cell.setImageType(CellImage.FLOOR);
                            map.setPlayerStartingPosition(new int[]{x, y});
                        }
                        default -> throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

    private static CellRenderType getMapCellRenderType(Scanner scanner){
        String mapType = scanner.nextLine();
        if(Objects.equals(mapType, "D")){
            return CellRenderType.DUNGEON;
        } else if (Objects.equals(mapType, "F")){
            return CellRenderType.FOREST;
        } else {
            return CellRenderType.DUNGEON;
        }
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }
}
