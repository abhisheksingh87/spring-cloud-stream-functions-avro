package com.wellsfargo.cto.eai.starter.kafka.cloudstreams.function.service;

import com.wellsfargo.cto.eai.starter.kafka.cloudstreams.function.dto.CardType;
import com.wellsfargo.cto.eai.starter.kafka.cloudstreams.function.dto.CreditCardDto;
import com.wellsfargo.cto.eai.starter.kafka.cloudstreams.function.dto.Customer;
import com.wellsfargo.cto.eai.starter.kafka.cloudstreams.function.event.card.CardEvent;
import com.wellsfargo.cto.eai.starter.kafka.cloudstreams.function.event.card.CardStatus;
import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CreditCardPublisher {

    private final Sinks.Many<CardEvent> cardSink;

    public void raiseCardEvent(final Customer customer, CardStatus cardStatus){
        var creditCardDto = CreditCardDto.of(
                UUID.randomUUID(),
                CardType.VISA,
                customer.getIncome(),
                null,
                customer.getCustomerId().toString(),
                cardStatus
        );
        var cardEvent = new CardEvent(creditCardDto, cardStatus);
        this.cardSink.tryEmitNext(cardEvent);
    }

}
