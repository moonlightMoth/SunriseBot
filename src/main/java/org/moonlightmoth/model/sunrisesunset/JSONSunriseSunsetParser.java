package org.moonlightmoth.model.sunrisesunset;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.moonlightmoth.model.SunstageExternalData;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JSONSunriseSunsetParser {
        private Map<String, String> results;
        private String sunrise;
        private String sunset;
        private String solarNoon;
        private String dayLength;


        JSONSunriseSunsetParser(@JsonProperty("results") Map<String, String> results) {
            this.results = results;
            this.sunrise = results.get("sunrise");
            this.sunset = results.get("sunset");
            this.dayLength = results.get("day_length");
            this.solarNoon = results.get("solar_noon");
        }

        public SunstageExternalData getSunstageData(OffsetDateTime date) {

            OffsetDateTime sunsetDate = OffsetDateTime.parse(sunset, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            OffsetDateTime sunriseDate = OffsetDateTime.parse(sunrise, DateTimeFormatter.ISO_OFFSET_DATE_TIME);

            return new SunstageExternalData(sunsetDate, sunriseDate, date);
        }


}
