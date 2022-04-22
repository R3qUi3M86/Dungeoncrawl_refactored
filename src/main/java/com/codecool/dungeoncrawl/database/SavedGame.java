package com.codecool.dungeoncrawl.database;

import com.codecool.dungeoncrawl.model.actors.Actor;
import com.codecool.dungeoncrawl.model.actors.Player;
import com.codecool.dungeoncrawl.model.decor.Decor;
import com.codecool.dungeoncrawl.model.items.Item;
import com.codecool.dungeoncrawl.model.map.GameMap;

public record SavedGame(Player player, Actor[][] actorMatrix, Item[][] itemMatrix, Decor[][] decorMatrix,
                        GameMap gameMap) {
}
