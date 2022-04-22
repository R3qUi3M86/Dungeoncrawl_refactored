package com.codecool.dungeoncrawl.database;
import org.postgresql.ds.PGSimpleDataSource;
import javax.sql.DataSource;
import java.sql.SQLException;

public class DbManager {
    public DataSource connect() throws SQLException{
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setDatabaseName("dungeon");
        dataSource.setUser("wika-dungeon");
        dataSource.setPassword("password");

        System.out.println("Trying to connect...");
        dataSource.getConnection().close();
        System.out.println("Connection OK");
        return dataSource;
    }
}
