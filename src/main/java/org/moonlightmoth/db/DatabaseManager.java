package org.moonlightmoth.db;

import org.moonlightmoth.util.Const;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public abstract class DatabaseManager {

    private Connection conn = null;

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

    public boolean connect(File dir, File dbFileName) throws IOException {
        try {
            String url = Const.sqliteJDBCPrefix + dir.getAbsolutePath() + "/" + dbFileName;
            if(!createDirIfNotExists(dir))
                throw new IOException("Can not create directory for Database, try checking rw permissions of current dir");
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
            return true;
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
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

    public boolean executeSQLStatement(String stm) {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(stm);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    private boolean createDirIfNotExists(File dir){
        if (dir.isDirectory())
            return true;
        else return dir.mkdir();
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

}
