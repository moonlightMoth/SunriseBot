package org.moonlightmoth.controller.datareceivers;

import org.moonlightmoth.model.ExternalData;

import java.time.OffsetDateTime;

public interface DataReceiver {
    public ExternalData receiveDataByDate(OffsetDateTime date, int userId);
}
