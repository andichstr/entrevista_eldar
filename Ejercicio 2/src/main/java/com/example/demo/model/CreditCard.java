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
public class CreditCard extends GenericModel {
    private CardBrand brand;
    private byte[] cardNumber;
    private String cardHolder;
    private Date expirationDate;
}
