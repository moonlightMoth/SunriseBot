package org.moonlightmoth.controller.datareceivers.sunstage;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.moonlightmoth.controller.datareceivers.DataReceiver;
import org.moonlightmoth.db.sunstage.SunstageDatabaseManager;
import org.moonlightmoth.model.SunstageData;
import org.moonlightmoth.model.sunrisesunset.JSONSunriseSunsetParser;
import org.moonlightmoth.util.Const;
import org.moonlightmoth.util.GeoPosition;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class SunstageDataReceiver implements DataReceiver {

    private static final SunstageDataReceiver sunstageDataReceiver = null;

    private SunstageDataReceiver()
    {

    }

    @Override
    public SunstageData receiveDataByDate(OffsetDateTime date, int userId) {
        ObjectMapper mapper = new ObjectMapper();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


        try (CloseableHttpClient client = HttpClients.createDefault())
        {
            GeoPosition geoPosition = new SunstageDatabaseManager().getGeoById(userId);

            HttpGet request = new HttpGet(Const.datelessSunstageGETURI +
                    "?lat=" + geoPosition.getLatitude() + "&lng=" +
                    geoPosition.getLongitude() + "&date=" +
                    dateTimeFormatter.format(date) + "&formatted=0");

            System.out.println(request);

            JSONSunriseSunsetParser response = client.execute(request, httpResponse ->
                    mapper.readValue(httpResponse.getEntity().getContent(), JSONSunriseSunsetParser.class));


            return response.getSunstageData(date);

        } catch (IOException e) {
            System.out.println("Cannot connect to source api or cannot parse source api response");
            e.printStackTrace();
        }
        return new SunstageData(date, date, date);
    }

    public static SunstageDataReceiver getInstance()
    {
        return Objects.requireNonNullElseGet(sunstageDataReceiver, SunstageDataReceiver::new);
    }
}
