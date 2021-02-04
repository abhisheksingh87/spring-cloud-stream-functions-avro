package com.wellsfargo.cto.eai.starter.kafka.cloudstreams.function.config;

import com.wellsfargo.cto.eai.starter.kafka.cloudstreams.function.event.card.CardEvent;
import com.wellsfargo.cto.eai.starter.kafka.cloudstreams.function.event.eventhandler.CardStatusUpdateEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Consumer;
import java.util.function.Supplier;


@Configuration
public class CreditCardConfig {

    @Autowired
    private CardStatusUpdateEventHandler cardStatusUpdateEventHandler;

    @Bean
    public Sinks.Many<CardEvent> cardSink(){
        return Sinks.many().unicast().onBackpressureBuffer();
    }

    @Bean
    public Supplier<Flux<CardEvent>> cardSupplier(Sinks.Many<CardEvent> sink){
        return sink::asFlux;
    }

    @Bean
    public Consumer<CardEvent> cardEventConsumer(){
        return cardEvent -> cardStatusUpdateEventHandler.updateCardStatus(cardEvent.getCreditCardDto());
    }
}
