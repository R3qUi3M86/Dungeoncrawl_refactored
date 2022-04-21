package com.codecool.dungeoncrawl.database;

import com.codecool.dungeoncrawl.model.actors.Actor;
import com.codecool.dungeoncrawl.model.actors.Player;

import javax.sql.DataSource;
import java.io.*;
import java.sql.*;

public class SavedGameDaoImpl implements SavedGameDao{
    private final DataSource dataSource;

    public SavedGameDaoImpl(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void create(SavedGame savedGame) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO saved_games (player, actor_matrix, item_matrix, decor_matrix, game_map) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            st.setBytes(1, serializeObject(savedGame.getPlayer()));
            st.setBytes(2, serializeObject(savedGame.getActorMatrix()));
            st.setBytes(3, serializeObject(savedGame.getPlayer()));
            st.setBytes(4, serializeObject(savedGame.getPlayer()));
            st.setBytes(5, serializeObject(savedGame.getPlayer()));
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public SavedGame read(int id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM saved_games WHERE id=?";
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, id);
            ResultSet queryResult = st.executeQuery();
            if (queryResult.next()) {
                byte[] playerBytes = queryResult.getBytes("player");
                Player player = deserializePlayer(playerBytes);
                byte[] actorMatrixBytes = queryResult.getBytes("actor_matrix");
                Actor[][] actorMatrix = deserializeActorMatrix(actorMatrixBytes);
                byte[] itemMatrixBytes = queryResult.getBytes("item_matrix");
                byte[] decorMatrixBytes = queryResult.getBytes("decor_matrix");
                byte[] gameMapBytes = queryResult.getBytes("game_map");
                return new SavedGame(player, actorMatrix);
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(SavedGame savedGame) {

    }

    @Override
    public void delete(SavedGame savedGame) {

    }

    private byte[] serializeObject(Object player){
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)){
            oos.writeObject(player);
            return bos.toByteArray();
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        }
    }

    private Player deserializePlayer(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        ObjectInputStream is = new ObjectInputStream(in);
        return (Player) is.readObject();
    }

    private Actor[][] deserializeActorMatrix(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        ObjectInputStream is = new ObjectInputStream(in);
        return ((Actor[][]) is.readObject());
    }
}
