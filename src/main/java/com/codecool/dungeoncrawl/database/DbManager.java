package com.codecool.dungeoncrawl.database;
import org.postgresql.ds.PGSimpleDataSource;
import javax.sql.DataSource;
import java.sql.SQLException;

public class DbManager {
    public DataSource connect() throws SQLException{
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setDatabaseName("dungeoncrawl");
        dataSource.setUser("Req-dungeoncrawl-codecool");
        dataSource.setPassword("ONE123ONE");

        System.out.println("Trying to connect...");
        dataSource.getConnection().close();
        System.out.println("Connection OK");
        return dataSource;
    }
}
