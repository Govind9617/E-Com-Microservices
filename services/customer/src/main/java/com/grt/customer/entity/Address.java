package com.grt.customer.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Builder
@Document
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class Address {
    private String street;
    private String houseNumber;
    private String zipCode;
}
