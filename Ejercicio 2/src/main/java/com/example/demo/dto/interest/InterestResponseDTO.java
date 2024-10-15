package com.example.demo.dto.interest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
/**
 * This DTO is used to return the calculated interest and the amount to pay to the client.
 */
public class InterestResponseDTO {
    private Float interest;
    private Float amount;
}
