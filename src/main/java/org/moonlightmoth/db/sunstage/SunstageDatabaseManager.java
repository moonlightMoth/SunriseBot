package org.moonlightmoth.db.sunstage;

import org.moonlightmoth.db.ConnectionManager;
import org.moonlightmoth.db.DatabaseManager;
import org.moonlightmoth.model.UserData;
import org.moonlightmoth.util.Const;
import org.moonlightmoth.model.userdata.GeoPosition;

import java.io.*;
import java.sql.*;

import static org.moonlightmoth.util.Util.getUserTimeZone;

public class SunstageDatabaseManager implements DatabaseManager {

    private final File sunsetDBDir = new File(Const.sunsetBotDBDir);
    private final File sunsetDBFileName = new File(Const.sunsetBotDBFile);
    private ConnectionManager cm = null;

    private final String tableCreationStm = "CREATE TABLE IF NOT EXISTS geopos (\n" +
            "\tuid INTEGER PRIMARY KEY,\n" +
            "\tgeopos BLOB NOT NULL\n" +
            ");";
    private final String insertSTM = "INSERT INTO geopos (uid, geopos) VALUES (?, ?);";
    private final String selectSTM = "SELECT geopos FROM geopos WHERE uid=?";

    public SunstageDatabaseManager() throws IOException
    {
        cm = new ConnectionManager(sunsetDBDir, sunsetDBFileName);
        createTableIfNotExists();
        //insertGeoposRecord(1, new GeoPosition(59.93, 30.31, getUserTimeZone()));
    }

    @Override
    public boolean insertOrAlterUserData(int uid, UserData userData) {
        if (!(userData instanceof GeoPosition))
            throw new IllegalArgumentException();
        return insertGeoposRecord(uid, (GeoPosition) userData);
    }

    @Override
    public UserData selectUserDataByID(int uid) {
        return getGeoposById(uid);
    }

    private boolean createTableIfNotExists()
    {
        PreparedStatement stm = null;
        boolean result = false;

        {
            try {
                stm = cm.getConnectionObject().prepareStatement(tableCreationStm);
                result = stm.execute();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }

        return result;
    }

    public boolean insertGeoposRecord(int uid, GeoPosition geoPosition) //TODO make safe insert if record exists
    {
        boolean result = false;
        PreparedStatement s;
        try {
            s = cm.getConnectionObject().prepareStatement(insertSTM);
            s.setInt(1, uid);
            s.setBytes(2, serializeGeopos(geoPosition));

            result = s.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public GeoPosition getGeoposById(int uid)
    {
        PreparedStatement s;
        try {
            s = cm.getConnectionObject().prepareStatement(selectSTM);
            s.setInt(1, uid);
            ResultSet rs = s.executeQuery();
            byte[] bytes = rs.getBytes("geopos");

            return deserialize(bytes);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return new GeoPosition(23,32, null);
    }

    private byte[] serializeGeopos(GeoPosition geo)
    {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(geo);
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[1];
    }

    private GeoPosition deserialize(byte[] bytes)
    {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois =  new ObjectInputStream(bais);
            return  (GeoPosition) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }
}
