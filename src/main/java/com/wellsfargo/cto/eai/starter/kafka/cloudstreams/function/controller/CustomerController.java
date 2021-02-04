package com.wellsfargo.cto.eai.starter.kafka.cloudstreams.function.controller;

import com.wellsfargo.cto.eai.starter.kafka.cloudstreams.function.dto.Customer;
import com.wellsfargo.cto.eai.starter.kafka.cloudstreams.function.event.card.CardStatus;
import com.wellsfargo.cto.eai.starter.kafka.cloudstreams.function.service.CreditCardPublisher;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController()
@RequestMapping("customer")
@AllArgsConstructor
public class CustomerController {

    private final CreditCardPublisher creditCardPublisher;

    @PostMapping("/create")
    public void createCustomer(@RequestBody Customer customer){
        customer.setCustomerId(UUID.randomUUID());
        this.creditCardPublisher.raiseCardEvent(customer, CardStatus.CARD_VERIFYING);
    }

}
