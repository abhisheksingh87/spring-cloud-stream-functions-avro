package com.wellsfargo.cto.eai.starter.kafka.cloudstreams.function.dto;

import com.wellsfargo.cto.eai.starter.kafka.cloudstreams.function.event.card.CardStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@ToString
public class CreditCardDto {

    private UUID id;
    private CardType cardType;
    private String customerIncome;
    private String amountLimit;
    private String customerId;
    private CardStatus cardStatus;
}
