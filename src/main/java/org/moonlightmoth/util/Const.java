package org.moonlightmoth.util;

public class Const {
    public final static String datelessSunstageGETURI =
            "https://api.sunrise-sunset.org/json?lat=" + Util.getUserGeoPos().getLatitude()
                    + "&lng=" + Util.getUserGeoPos().getLongitude()
                    + "&date=";
    public static final String sunstageResource = "api.sunrise-sunset.org";
}
