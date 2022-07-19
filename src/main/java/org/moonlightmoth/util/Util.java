package org.moonlightmoth.util;

import java.time.ZoneOffset;

public class Util {
    public static ZoneOffset getUserTimeZone() { return ZoneOffset.ofHours(3); }
    public static GeoPosition getUserGeoPos() { return new GeoPosition(59.56, 30.19); }


}
