package org.moonlightmoth.db.sunstage;

import org.moonlightmoth.db.DatabaseManager;
import org.moonlightmoth.util.Const;
import org.moonlightmoth.util.GeoPosition;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.Objects;

public class SunstageDatabaseManager extends DatabaseManager {

    private final File sunsetDBDir = new File(Const.sunsetBotDBDir);
    private final File sunsetDBFileName = new File(Const.sunsetBotDBFile);

    private final String tableCreationStm = "CREATE TABLE IF NOT EXISTS geopos (\n" +
            "\tuid INTEGER PRIMARY KEY,\n" +
            "\tgeopos BLOB NOT NULL\n" +
            ");";

    private final String insertSTM = "INSERT INTO geopos (uid, geopos) VALUES (";

    public SunstageDatabaseManager() throws IOException
    {
        connect();
        createTableIfNotExists();
        insertRecord(12, new GeoPosition(59.56, 30.19));
    }

    public boolean connect() throws IOException {
        return super.connect(sunsetDBDir, sunsetDBFileName);
    }

    public GeoPosition getGeoById(int uId)
    {
        return new GeoPosition(59.56, 30.19);
    }

    public boolean createTableIfNotExists()
    {
        return executeStatement(tableCreationStm);
    }

    public boolean insertRecord(int uid, GeoPosition geoPosition)
    {
        String st;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            new ObjectOutputStream(bos).writeObject(geoPosition);
            Blob blob = new SerialBlob(bos.toByteArray());

            st = insertSTM + uid + ", '" + blob + "');";
            System.out.println(st);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SerialException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return executeStatement(st);
    }

    private boolean executeStatement(String stm) {
        return super.executeSQLStatement(stm);
    }
}
