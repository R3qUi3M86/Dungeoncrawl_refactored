package com.codecool.dungeoncrawl.database;

import com.codecool.dungeoncrawl.model.actors.Actor;
import com.codecool.dungeoncrawl.model.actors.Player;
import com.codecool.dungeoncrawl.model.decor.Decor;
import com.codecool.dungeoncrawl.model.items.Item;
import com.codecool.dungeoncrawl.model.map.GameMap;

import javax.sql.DataSource;
import java.io.*;
import java.sql.*;

public record SavedGameDaoImpl(DataSource dataSource) implements SavedGameDao {

    @Override
    public void create(SavedGame savedGame) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO saved_games (player, actor_matrix, item_matrix, decor_matrix, game_map) VALUES (?, ?, ?, ?, ?)";
            setSQLParameters(savedGame, connection, sql);
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
                Player player = (Player) deserializeObject(playerBytes);
                byte[] actorMatrixBytes = queryResult.getBytes("actor_matrix");
                Actor[][] actorMatrix = (Actor[][]) deserializeObject(actorMatrixBytes);
                byte[] itemMatrixBytes = queryResult.getBytes("item_matrix");
                Item[][] itemMatrix = (Item[][]) deserializeObject(itemMatrixBytes);
                byte[] decorMatrixBytes = queryResult.getBytes("decor_matrix");
                Decor[][] decorMatrix = (Decor[][]) deserializeObject(decorMatrixBytes);
                byte[] gameMapBytes = queryResult.getBytes("game_map");
                GameMap map = (GameMap) deserializeObject(gameMapBytes);
                return new SavedGame(player, actorMatrix, itemMatrix, decorMatrix, map);
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(SavedGame savedGame) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE saved_games SET " + "player=?, actor_matrix=?, item_matrix=?, decor_matrix=?, game_map=? WHERE id=1";
            setSQLParameters(savedGame, connection, sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM saved_games WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, id);
            st.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setSQLParameters(SavedGame savedGame, Connection connection, String sql) throws SQLException {
        PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        st.setBytes(1, serializeObject(savedGame.player()));
        st.setBytes(2, serializeObject(savedGame.actorMatrix()));
        st.setBytes(3, serializeObject(savedGame.itemMatrix()));
        st.setBytes(4, serializeObject(savedGame.decorMatrix()));
        st.setBytes(5, serializeObject(savedGame.gameMap()));
        st.executeUpdate();
    }

    private byte[] serializeObject(Object player) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(player);
            return bos.toByteArray();
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        }
    }

    private Object deserializeObject(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }

}
