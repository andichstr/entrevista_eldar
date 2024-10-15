package com.example.demo.model;

import com.example.demo.dto.creditCard.CardBrand;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
/**
 * Model of the credit card in our database.
 * The card number is encrypted with a simple encryption method.
 */
public class CreditCard extends GenericModel {
    private CardBrand brand;
    private byte[] cardNumber;
    private String cardHolder;
    private Date expirationDate;
}
