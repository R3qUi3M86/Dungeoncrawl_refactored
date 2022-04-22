package com.codecool.dungeoncrawl.database;

public interface SavedGameDao {
    void create(SavedGame savedGame);

    SavedGame read(int id);

    void update(SavedGame savedGame);

    void delete(int id);
}
