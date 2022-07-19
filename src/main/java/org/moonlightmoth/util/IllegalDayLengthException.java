package org.moonlightmoth.util;

import java.io.IOException;
import java.util.IllegalFormatException;

public class IllegalDayLengthException extends IllegalArgumentException {
    @Override
    public String getMessage() {
        return super.getMessage() + "day can't be longer than 24h";
    }
}
