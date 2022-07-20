package org.moonlightmoth.db;

import org.moonlightmoth.util.GeoPosition;

public class DatabaseManager {

    public DatabaseManager()
    {

    }

    public GeoPosition getGeoById(int userId)
    {
        return new GeoPosition(59.56, 30.19);
    }
}
