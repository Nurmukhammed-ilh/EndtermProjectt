package com.company.data;

import com.company.data.interfaces.IDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDB implements IDB {
    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        String connectionUrl = "jdbc:postgresql://localhost:5432/postgres";
        try {  // Here we load the driver’s class file into memory at the runtime
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(connectionUrl, "postgres", "56964nurmuh");//password
            return con; //connect
        } catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
}
