package com.wellsfargo.cto.eai.starter.kafka.cloudstreams.function.event;

import java.util.Date;
import java.util.UUID;

public interface Event {

    UUID getEventId();
    Date getDate();

}
