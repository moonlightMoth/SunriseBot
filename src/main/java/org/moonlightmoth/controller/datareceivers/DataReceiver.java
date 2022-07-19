package org.moonlightmoth.controller.datareceivers;

import org.moonlightmoth.model.Data;
import org.moonlightmoth.model.SunstageData;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.Objects;

public interface DataReceiver {
    public Data receiveDataByDate(OffsetDateTime date);
}
