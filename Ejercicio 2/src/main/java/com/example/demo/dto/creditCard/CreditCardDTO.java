package com.example.demo.dto.creditCard;

import com.example.demo.dto.ModelDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
/**
 * This DTO represents a credit card model, used to transport
 * data between the clients and the server.
 */
public class CreditCardDTO extends ModelDTO {
    private CardBrand brand;
    private String cardNumber;
    private String cardHolder;
    private Date expirationDate;
}
