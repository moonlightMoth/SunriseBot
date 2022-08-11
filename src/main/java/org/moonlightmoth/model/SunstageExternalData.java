package org.moonlightmoth.model;

import org.moonlightmoth.util.IllegalDayLengthException;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Objects;

public class SunstageExternalData implements ExternalData {
    private OffsetDateTime sunsetDate;
    private OffsetDateTime sunriseDate;
    private OffsetDateTime date;
    private Duration dayLength = null;


    public SunstageExternalData(OffsetDateTime sunsetDate, OffsetDateTime sunriseDate, OffsetDateTime date)
    {
        setSunsetDate(sunsetDate);
        setSunriseDate(sunriseDate);
        setDate(date);
    }

    void setSunsetDate(OffsetDateTime sunsetDate) {
        this.sunsetDate = sunsetDate;
    }

    public OffsetDateTime getSunsetDate() {
        return sunsetDate;
    }

    public OffsetDateTime getSunriseDate()
    {
        return sunriseDate;
    }

    void setSunriseDate(OffsetDateTime sunriseDate) {
        this.sunriseDate = sunriseDate;
    }

    void setDayLength(Duration dayLength) {
        if(dayLength.toSeconds() > 86400)
            throw new IllegalDayLengthException();
        this.dayLength = dayLength;
    }


    public OffsetDateTime getDate() {
        return date;
    }

    void setDate(OffsetDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SunstageData{" +
                "sunsetDate=" + sunsetDate +
                ", sunriseDate=" + sunriseDate +
                ", date=" + date +
                ", dayLength=" + dayLength +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SunstageExternalData that = (SunstageExternalData) o;
        return Objects.equals(sunsetDate, that.sunsetDate) && Objects.equals(sunriseDate, that.sunriseDate) && Objects.equals(date, that.date) && Objects.equals(dayLength, that.dayLength);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sunsetDate, sunriseDate, date, dayLength);
    }
}
