package org.moonlightmoth;

import org.moonlightmoth.controller.datareceivers.SunstageDataReceiver;
import org.moonlightmoth.model.SunstageData;
import org.moonlightmoth.util.Util;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        SunstageDataReceiver sb = SunstageDataReceiver.getInstance();

        SunstageData sunstageData = sb.receiveDataByDate(OffsetDateTime.now(), 1);
        DateTimeFormatter dtf = DateTimeFormatter.RFC_1123_DATE_TIME;

        System.out.println("Sunset:");
        System.out.println(dtf.format(sunstageData.getSunsetDate().withOffsetSameInstant(Util.getUserTimeZone())));
        System.out.println("Sunrise:");
        System.out.println(dtf.format(sunstageData.getSunriseDate().withOffsetSameInstant(Util.getUserTimeZone())));

    }
}