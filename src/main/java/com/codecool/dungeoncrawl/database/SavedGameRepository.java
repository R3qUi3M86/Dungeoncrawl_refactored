package com.codecool.dungeoncrawl.database;

public interface SavedGameRepository {
    SavedGame get(int id);

    void add(SavedGame savedGame);

    void update(SavedGame savedGame);

    void remove(int id);
}
