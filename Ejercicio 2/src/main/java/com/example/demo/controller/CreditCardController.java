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
public class CreditCardController {
    @Autowired @Getter private ModelMapper modelMapper;
    @Autowired @Getter private CreditCardService service;

    @PostMapping("/calculate-interest")
    public ResponseEntity<String> calculateInterest(@RequestBody InterestDTO dto) {
        try {
            Float interest = getService().calculateInterest(dto);
            return ResponseEntity.ok("The interest of this operation will be: " + interest);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error calculating the interest for this operation.");
        }
    }
}
