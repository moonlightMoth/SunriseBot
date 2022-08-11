package org.moonlightmoth.util;

import org.moonlightmoth.model.userdata.GeoPosition;

import java.time.ZoneOffset;

public class Util {
    public static ZoneOffset getUserTimeZone() { return ZoneOffset.ofHours(3); }
    public static GeoPosition getUserGeoPos() { return new GeoPosition(59.93, 30.31, getUserTimeZone()); }


}
