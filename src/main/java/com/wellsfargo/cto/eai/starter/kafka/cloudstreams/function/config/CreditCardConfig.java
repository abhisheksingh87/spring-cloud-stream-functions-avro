package com.wellsfargo.cto.eai.starter.kafka.cloudstreams.function.config;

import com.wellsfargo.cto.eai.starter.kafka.cloudstreams.function.event.card.CardEvent;
import com.wellsfargo.cto.eai.starter.kafka.cloudstreams.function.event.eventhandler.CardStatusUpdateEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;


@Configuration
public class CreditCardConfig {

    @Autowired
    private CardStatusUpdateEventHandler cardStatusUpdateEventHandler;

    /**
     * {@link Sinks} are a special kind of Publisher that are also a Subscriber.
     * These expose standalone sinks expose tryEmit methods which is used by
     * {@link com.wellsfargo.cto.eai.starter.kafka.cloudstreams.function.service.CreditCardPublisher} class
     * to emit CardEvent
     *
     */
    @Bean
    public Sinks.Many<CardEvent> cardSink(){
        return Sinks.many().unicast().onBackpressureBuffer();
    }

    /**
     * {@link Sinks} are a special kind of Publisher that are also a Subscriber.
     * These expose standalone sinks expose tryEmit methods that return an Sinks.EmitResult enum,
     * allowing to atomically fail in case the attempted signal is inconsistent with the spec
     * and/or the state of the sink.
     *
     */
    @Bean
    public Supplier<Flux<CardEvent>> cardSupplier(Sinks.Many<CardEvent> sink){
        return sink::asFlux;
    }

    @Bean
    public Consumer<CardEvent> cardEventConsumer(){
        return cardEvent -> cardStatusUpdateEventHandler.updateCardStatus(cardEvent.getCreditCardDto());
    }

    @Bean
    public Function<Flux<String>, Flux<String>> uppercase() {
        return flux -> flux.map(String::toUpperCase);
    }
}
