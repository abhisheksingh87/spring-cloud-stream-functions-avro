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

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CreditCardPublisher {

    private final Sinks.Many<CardEvent> cardSink;

    public void raiseCardEvent(final Customer customer, CardStatus cardStatus) {
        var creditCardDto = CreditCardDto.newBuilder()
                .setCustomerId(customer.getCustomerId().toString())
                .setCardStatus(CardStatus.CARD_VERIFYING)
                .setCustomerIncome(customer.getIncome())
                .setAmountLimit("100")
                .setCardType(CardType.Master)
                .setId(UUID.randomUUID().toString()).build();

        var cardEvent = CardEvent.newBuilder()
                .setEventId(UUID.randomUUID().toString())
                .setCreditCardDto(creditCardDto)
                .setCardStatus(cardStatus)
                .setDate(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))
                .build();
        this.cardSink.tryEmitNext(cardEvent);
    }

}
