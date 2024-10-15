package com.example.demo.controller;

import com.example.demo.dto.creditCard.InterestDTO;
import com.example.demo.service.CreditCardService;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credit-card")
/**
 * Controller that handles request to calculate the interest of an operation.
 */
public class CreditCardController {
    @Autowired @Getter private ModelMapper modelMapper;
    @Autowired @Getter private CreditCardService service;

    @PostMapping("/calculate-interest")
    public ResponseEntity<String> calculateInterest(@RequestBody InterestDTO dto) {
        try {
            Float interest = getService().calculateInterest(dto);
            Float total = dto.getAmount() + (dto.getAmount() * interest);
            return ResponseEntity.ok("The interest of this operation will be: " + interest + ", and the total to pay is: " + total);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error calculating the interest for this operation.");
        }
    }
}
