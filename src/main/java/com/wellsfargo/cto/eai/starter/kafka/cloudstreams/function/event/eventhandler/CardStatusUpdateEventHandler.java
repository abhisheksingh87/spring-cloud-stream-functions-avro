package com.wellsfargo.cto.eai.starter.kafka.cloudstreams.function.event.eventhandler;

import com.wellsfargo.cto.eai.starter.kafka.cloudstreams.function.dto.CreditCardDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CardStatusUpdateEventHandler {

    public void updateCardStatus(CreditCardDto creditCardDto){
        log.info("Consumed Card Details: {}", creditCardDto);

    }
}
