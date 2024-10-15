package com.example.demo.service;

import com.example.demo.dto.creditCard.CardBrand;
import com.example.demo.dto.creditCard.CreditCardDTO;
import com.example.demo.dto.creditCard.InterestDTO;
import com.example.demo.model.CreditCard;
import com.example.demo.repository.CreditCardRepository;
import com.example.demo.utils.EncryptUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CreditCardService implements GenericService<CreditCard, CreditCardRepository> {
    private ModelMapper modelMapper;
    private CreditCardRepository creditCardRepository;
    private static final int CREDIT_CARD_NUMBER_LENGHT = 15;
    // FIXME this should be in a secret properties file, or in an environment variable
    // For this example, i will keep this here
    private static final String ENCRYPT_KEY = "Y2lhbzU2Nzg5MTIzNDU2Cg==";
    private static final Float MINIMUM_INTEREST = 0.3F;
    private static final Float MAXIMUM_INTEREST = 5F;


    @Override
    public ModelMapper getModelMapper() {
        return this.modelMapper;
    }

    @Override
    public CreditCardRepository getRepository() {
        return this.creditCardRepository;
    }

    /**
     * Method to validate fields from a credit card.
     * @param dto credit card
     * @return false if any field is invalid, true if all fields are valid
     */
    private boolean validateFields(CreditCardDTO dto) {
        // If any of the required fields are null, return false
        if(Objects.isNull(dto.getBrand()) ||
                Objects.isNull(dto.getCardNumber()) ||
                Objects.isNull(dto.getCardHolder()) ||
                Objects.isNull(dto.getExpirationDate())) {
            return false;
        }

        // Card number validation
        // If the credit card number is less than the minimum card number, or more than the maximum card number, return false
        if (dto.getCardNumber().length() != CREDIT_CARD_NUMBER_LENGHT) {
            return false;
        }

        //Try to parse each character into a long, if it can't, it means it's not a number
        try {
            for (int i = 0; i < dto.getCardNumber().length(); i++){
                char c = dto.getCardNumber().charAt(i);
                Long.parseLong(String.valueOf(c));
            }
        } catch (NumberFormatException e) {
            return false;
        }

        // Check if brand is currently available on the system
        List<CardBrand> cardBrands = List.of(CardBrand.values());
        if(!cardBrands.contains(dto.getBrand())) {
            return false;
        }

        return true;
    }

    /**
     * Creates a credit card entity, if all the fields are valid.
     * @param dto object containing all the data needed to create a credit card.
     * @return the credit card entity already created.
     */
    public CreditCard createFromDTO(CreditCardDTO dto) {
        if (!this.validateFields(dto)) {
            return null;
        }

        EncryptUtil encryptUtil = new EncryptUtil(ENCRYPT_KEY);

        CreditCard creditCard = new CreditCard(
                dto.getBrand(),
                encryptUtil.encrypt(dto.getCardNumber()),
                dto.getCardHolder(),
                dto.getExpirationDate());

        return creditCard;
    }

    /**
     * Creates and persist an element from a CreditCardDTO
     * @param dto the credit card dto containing the data to create the credit card.
     * @return the credit card entity already persisted.
     */
    public CreditCard persistFromDTO(CreditCardDTO dto) {
        return getRepository().save(this.createFromDTO(dto));
    }

    /**
     * This method updates a model already existing in the database
     * @param dto the dto with the data to update
     * @param id the ID of the element to update
     * @return the element already updated.
     */
    public CreditCard updateFromDTO(CreditCardDTO dto, Long id) {
        CreditCard model = getRepository().findById(id).orElse(null);
        if (Objects.isNull(model)) {
            return null;
        }

        if (Objects.nonNull(dto.getCardNumber())) {
            EncryptUtil encryptUtil = new EncryptUtil(ENCRYPT_KEY);
            model.setCardNumber(encryptUtil.encrypt(dto.getCardNumber()));
        }

        if (Objects.nonNull(dto.getExpirationDate())) {
            model.setExpirationDate(dto.getExpirationDate());
        }

        if (Objects.nonNull(dto.getCardHolder())) {
            model.setCardHolder(dto.getCardHolder());
        }

        if (Objects.nonNull(dto.getBrand())) {
            model.setBrand(dto.getBrand());
        }

        return model;
    }

    /**
     * Method to calculate the interest that every brand will apply to the amount to pay.
     * @param dto the JSON request body
     * @return the interest as a float
     */
    public Float calculateInterest(InterestDTO dto) {
        Calendar calendar = Calendar.getInstance();
        int day, month, year;
        Float interest = 0f;
        switch (dto.getBrand()) {
            // last 2 digits of year / month
            case VISA:
                String yearString = String.valueOf(calendar.get(Calendar.YEAR));
                year = Integer.parseInt(yearString.substring(yearString.length() - 2));
                day = calendar.get(Calendar.DAY_OF_MONTH);
                interest = (float) year/day;
                break;
            // day * 0.5
            case NARA:
                day = calendar.get(Calendar.DAY_OF_MONTH);
                interest = day * 0.5F;
                break;
            // month * 0.1
            case AMEX:
                month = calendar.get(Calendar.MONTH);
                interest = month * 0.1F;
        }

        if (interest < MINIMUM_INTEREST) return MINIMUM_INTEREST;
        if (interest > MAXIMUM_INTEREST) return MAXIMUM_INTEREST;
        return interest;
    }
}
