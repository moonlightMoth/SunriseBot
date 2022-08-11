package org.moonlightmoth;

import org.moonlightmoth.controller.datareceivers.sunstage.SunstageDataReceiver;
import org.moonlightmoth.model.SunstageExternalData;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        SunstageDataReceiver sb = SunstageDataReceiver.getInstance();


        SunstageExternalData sunstageData = sb.receiveDataByDate(OffsetDateTime.now(), 1);
        DateTimeFormatter dtf = DateTimeFormatter.RFC_1123_DATE_TIME;

        System.out.println("Sunset:");
        System.out.println(dtf.format(sunstageData.getSunsetDate().withOffsetSameInstant(sunstageData.getDate().getOffset())));
        System.out.println("Sunrise:");
        System.out.println(dtf.format(sunstageData.getSunriseDate().withOffsetSameInstant(sunstageData.getDate().getOffset())));


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