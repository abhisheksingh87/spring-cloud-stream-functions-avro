package com.wellsfargo.cto.eai.starter.kafka.cloudstreams.function.event.card;

import com.wellsfargo.cto.eai.starter.kafka.cloudstreams.function.dto.CreditCardDto;
import com.wellsfargo.cto.eai.starter.kafka.cloudstreams.function.event.Event;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class CardEvent implements Event {

    private final UUID eventId = UUID.randomUUID();
    private final Date date = new Date();
    private CreditCardDto creditCardDto;
    private CardStatus cardStatus;

    public CardEvent(CreditCardDto creditCardDto, CardStatus cardStatus) {
        this.creditCardDto = creditCardDto;
        this.cardStatus = cardStatus;
    }

    @Override
    public UUID getEventId() {
        return this.eventId;
    }

    @Override
    public Date getDate() {
        return this.date;
    }

}
