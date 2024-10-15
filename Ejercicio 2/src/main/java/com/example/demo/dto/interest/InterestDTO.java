package com.example.demo.dto.interest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
/**
 * This DTO is used to calculate the interests of a transaction,
 * based on the brand of the credit card.
 */
public class InterestDTO {
    private String brand;
    private Integer amount;
}
