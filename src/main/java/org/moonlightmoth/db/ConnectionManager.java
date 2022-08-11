package org.moonlightmoth.db;

import org.moonlightmoth.util.Const;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class ConnectionManager {

    private Connection conn = null;

    public ConnectionManager(File dir, File file) throws IOException
    {
        connect(dir, file);
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

    private boolean connect(File dir, File dbFileName) throws IOException {
        try {
            String url = Const.sqliteJDBCPrefix + dir.getAbsolutePath() + "/" + dbFileName;
            if(!createDirIfNotExists(dir))
                throw new IOException("Can not create directory for Database, try checking rw permissions of current dir");
            conn = DriverManager.getConnection(url);
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

    public Connection getConnectionObject()
    {
        return conn;
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
