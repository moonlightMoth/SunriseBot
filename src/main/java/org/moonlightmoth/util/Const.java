package org.moonlightmoth.util;

public class Const {
    public final static String datelessSunstageGETURI =
            "http://api.sunrise-sunset.org/json"
                    + "?date=";
    public static final String sunstageResource = "api.sunrise-sunset.org";

    public static final String sunsetBotDBDir = "db";
    public static final String sunsetBotDBFile = "SunsetData.db";
    public static final String sunsetBotDBURL = sunsetBotDBDir + "/" + sunsetBotDBFile;

    public static final String sqliteJDBCPrefix = "jdbc:sqlite:";
}
