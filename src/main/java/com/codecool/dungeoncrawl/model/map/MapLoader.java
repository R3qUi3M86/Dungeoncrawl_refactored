package com.codecool.dungeoncrawl.model.map;

import com.codecool.dungeoncrawl.controller.GameController;
import com.codecool.dungeoncrawl.controller.gameSubcontrollers.EntityControllers.ActorController;
import com.codecool.dungeoncrawl.controller.gameSubcontrollers.EntityControllers.DecorController;
import com.codecool.dungeoncrawl.controller.gameSubcontrollers.EntityControllers.ItemController;
import com.codecool.dungeoncrawl.display.cells.Cell;
import com.codecool.dungeoncrawl.display.cells.CellImage;
import com.codecool.dungeoncrawl.display.cells.CellRenderType;
import com.codecool.dungeoncrawl.display.cells.CellType;
import com.codecool.dungeoncrawl.model.actors.Rat;
import com.codecool.dungeoncrawl.model.actors.Skeleton;
import com.codecool.dungeoncrawl.model.actors.Warlock;
import com.codecool.dungeoncrawl.model.decor.SpiderWeb;
import com.codecool.dungeoncrawl.model.items.Booze;
import com.codecool.dungeoncrawl.model.items.Key;
import com.codecool.dungeoncrawl.model.items.Meat;
import com.codecool.dungeoncrawl.model.items.Sword;

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
                        case ' ':
                            cell.setType(CellType.ILLEGAL);
                            cell.setImageType(CellImage.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.COLLISION);
                            cell.setImageType(CellImage.WALL);
                            break;
                        case 'D':
                            cell.setType(CellType.INTERACTION);
                            cell.setImageType(CellImage.CLOSED_DOOR);
                            break;
                        case 'd':
                            cell.setType(CellType.WALKABLE);
                            cell.setImageType(CellImage.OPENED_DOOR);
                            break;
                        case '^':
                            cell.setType(CellType.WALKABLE);
                            cell.setImageType(CellImage.STAIRS_UP);
                            break;
                        case 'v':
                            cell.setType(CellType.WALKABLE);
                            cell.setImageType(CellImage.STAIRS_DOWN);
                            break;
                        case '.':
                            cell.setType(CellType.WALKABLE);
                            cell.setImageType(CellImage.FLOOR);
                            break;
                        case 'z':
                            cell.setType(CellType.INTERACTION);
                            cell.setImageType(CellImage.FLOOR);
                            decorController.addDecorToController(x, y, new SpiderWeb(cell));
                            break;
                        case 'E':
                            cell.setType(CellType.WALKABLE);
                            cell.setImageType(CellImage.FLOOR);
                            cell.setExit(true);
                            break;
                        case 'r':
                            cell.setType(CellType.WALKABLE);
                            cell.setImageType(CellImage.FLOOR);
                            actorController.addNpcToController(x, y, new Rat(cell));
                            break;
                        case 's':
                            cell.setType(CellType.WALKABLE);
                            cell.setImageType(CellImage.FLOOR);
                            actorController.addNpcToController(x, y, new Skeleton(cell));
                            break;
                        case 'o':
                            cell.setType(CellType.WALKABLE);
                            cell.setImageType(CellImage.FLOOR);
                            actorController.addNpcToController(x, y, new Warlock(cell));
                            break;
                        case 'k':
                            cell.setType(CellType.WALKABLE);
                            cell.setImageType(CellImage.FLOOR);
                            itemController.addItemToController(x, y, new Key(cell));
                            break;
                        case 'm':
                            cell.setType(CellType.WALKABLE);
                            cell.setImageType(CellImage.FLOOR);
                            itemController.addItemToController(x, y, new Meat(cell));
                            break;
                        case 'b':
                            cell.setType(CellType.WALKABLE);
                            cell.setImageType(CellImage.FLOOR);
                            itemController.addItemToController(x, y, new Booze(cell));
                            break;
                        case 'w':
                            cell.setType(CellType.WALKABLE);
                            cell.setImageType(CellImage.FLOOR);
                            itemController.addItemToController(x, y, new Sword(cell));
                            break;
                        case '@':
                            cell.setType(CellType.WALKABLE);
                            cell.setImageType(CellImage.FLOOR);
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
