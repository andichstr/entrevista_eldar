package com.example.demo.controller;

import com.example.demo.dto.ErrorResponseDTO;
import com.example.demo.dto.interest.InterestDTO;
import com.example.demo.dto.interest.InterestResponseDTO;
import com.example.demo.service.CreditCardService;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credit-card")
/*
* Controller that handles request to calculate the interest of an operation.
*/
public class CreditCardController {
    @Autowired @Getter private ModelMapper modelMapper;
    @Autowired @Getter private CreditCardService service;

    @PostMapping("/calculate-interest")
    public ResponseEntity<InterestResponseDTO> calculateInterest(@RequestBody InterestDTO dto) {
        Float interest = getService().calculateInterest(dto);
        Float total = getService().calculateTotal(dto.getAmount(), interest);
        InterestResponseDTO response = new InterestResponseDTO(interest, total);
        return ResponseEntity.ok(response);
    }
}
