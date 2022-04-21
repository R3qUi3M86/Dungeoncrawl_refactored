package com.codecool.dungeoncrawl.database;

public class SavedGameRepositoryImpl implements SavedGameRepository{
    private SavedGameDao userDaoImpl;

    public SavedGameRepositoryImpl(SavedGameDao savedGameDao){
        userDaoImpl = savedGameDao;
    }

    @Override
    public SavedGame get(int id) {
        return userDaoImpl.read(id);
    }

    @Override
    public void add(SavedGame savedGame) {
        userDaoImpl.create(savedGame);
    }

    @Override
    public void update(SavedGame savedGame) {
        userDaoImpl.update(savedGame);
    }

    @Override
    public void remove(SavedGame savedGame) {
        userDaoImpl.delete(savedGame);
    }
}
