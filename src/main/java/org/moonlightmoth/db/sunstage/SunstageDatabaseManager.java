package org.moonlightmoth.db.sunstage;

import org.moonlightmoth.util.Const;
import org.moonlightmoth.util.GeoPosition;

import java.io.File;
import java.sql.*;
import java.util.Objects;

public class SunstageDatabaseManager {

    private Connection conn = null;

    public SunstageDatabaseManager()
    {

    }

    public boolean isConnected()
    {
        try {
            return Objects.nonNull(conn) && !conn.isClosed();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean disconnect()
    {
        if (Objects.nonNull(conn)) {
            try {
                conn.close();
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return false;
    }

    public GeoPosition getGeoById(int userId)
    {
        return new GeoPosition(59.56, 30.19);
    }

    public boolean connect() {

        try {
            File file = new File(Const.sunsetBotDBURL);
            System.out.println(file.getAbsolutePath());
            conn = DriverManager.getConnection(Const.sunsetBotDBURL);

            System.out.println("Connection to SQLite has been established.");

            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
        return false;
    }

    public void createNewTable() {
        // SQLite connection string
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS employees (\n"
                + " id integer PRIMARY KEY,\n"
                + " name text NOT NULL,\n"
                + " capacity real\n"
                + ");";

        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void createNewDatabase() {

        try {

            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void closeConnection()
    {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
