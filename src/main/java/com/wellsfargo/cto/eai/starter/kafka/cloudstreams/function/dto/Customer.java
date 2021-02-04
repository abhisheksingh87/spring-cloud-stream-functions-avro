package com.wellsfargo.cto.eai.starter.kafka.cloudstreams.function.dto;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Customer {

    private UUID customerId;
    private String firstName;
    private String lastName;
    private String social;
    private String income;
}
