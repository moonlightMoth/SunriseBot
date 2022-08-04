package org.moonlightmoth;

import org.moonlightmoth.controller.datareceivers.sunstage.SunstageDataReceiver;
import org.moonlightmoth.db.sunstage.SunstageDatabaseManager;
import org.moonlightmoth.model.SunstageData;
import org.moonlightmoth.util.Util;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        SunstageDataReceiver sb = SunstageDataReceiver.getInstance();

        SunstageData sunstageData = sb.receiveDataByDate(OffsetDateTime.now(), 1);
        DateTimeFormatter dtf = DateTimeFormatter.RFC_1123_DATE_TIME;

        System.out.println("Sunset:");
        System.out.println(dtf.format(sunstageData.getSunsetDate().withOffsetSameInstant(Util.getUserTimeZone())));
        System.out.println("Sunrise:");
        System.out.println(dtf.format(sunstageData.getSunriseDate().withOffsetSameInstant(Util.getUserTimeZone())));


//        try
//        {
//            SunstageDatabaseManager sunstageDatabaseManager = new SunstageDatabaseManager();
//        }
//        catch (IOException e)
//        {
//            System.out.println(e.getMessage());;
//                    e.printStackTrace();
//        }
    }
}