package org.moonlightmoth.model.userdata;

import org.moonlightmoth.model.UserData;
import org.moonlightmoth.util.IllegalLocationException;

import java.io.Serializable;
import java.time.ZoneOffset;

public class GeoPosition implements UserData {
    private final double latitude;
    private final double longitude;
    private final ZoneOffset offset;
    private static final long serialVersionUID = 11824699L;

    public GeoPosition(double latitude, double longitude, ZoneOffset offset)
    {
        if ((latitude >= -90 && latitude <= 90) && (longitude >= -90 && longitude <= 90))
        {
            this.latitude = latitude;
            this.longitude = longitude;
            this.offset = offset;
        }
        else
            throw new IllegalLocationException();
    }

    public double getLatitude() {
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public ZoneOffset getOffset()
    {
        return offset;
    }

    @Override
    public String toString() {
        return "GeoPosition{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", offset=" + offset +
                '}';
    }
}
