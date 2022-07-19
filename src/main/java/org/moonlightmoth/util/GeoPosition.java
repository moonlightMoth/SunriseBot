package org.moonlightmoth.util;

import java.io.Serializable;

public class GeoPosition implements Serializable {
    private final double latitude;
    private final double longitude;

    public GeoPosition(double latitude, double longitude)
    {
        if ((latitude >= -90 && latitude <= 90) && (longitude >= -90 && longitude <= 90))
        {
            this.latitude = latitude;
            this.longitude = longitude;
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
}
