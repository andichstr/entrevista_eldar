import java.util.*;

public class Main {
    public static void main(String[] args) {

        EncryptUtil encryptUtil = new EncryptUtil("Y2lhbzU2Nzg5MTIzNDU2Cg==");
        //Calendar instance to handle expiration dates.
        Calendar calendar = Calendar.getInstance();
        calendar.set(2026, Calendar.AUGUST, 23);

        //We instantiate the 3 objects.
        //Visa card object
        CreditCard visaCard = new CreditCard(
                CardBrand.VISA,
                encryptUtil.encrypt("1111222233334444"),
                "Peter Shawn",
                calendar.getTime());
        calendar.set(2028, Calendar.MARCH, 13);
        //Nara card object
        CreditCard naraCard = new CreditCard(
                CardBrand.NARA,
                encryptUtil.encrypt("2222333344445555"),
                "Sofia Miller",
                calendar.getTime());
        calendar.set(2030, Calendar.JULY, 17);
        //Amex card object
        CreditCard amexCard = new CreditCard(
                CardBrand.AMEX,
                encryptUtil.encrypt("3333444455556666"),
                "Andrew Marshall",
                calendar.getTime());

        //Structure with sample cards
        Map<CardBrand, CreditCard> sampleCards = new HashMap<>();
        sampleCards.put(CardBrand.VISA, visaCard);
        sampleCards.put(CardBrand.NARA, naraCard);
        sampleCards.put(CardBrand.AMEX, amexCard);

        Scanner scanner = new Scanner(System.in);
        int ammount;
        String brandString;
        CardBrand cardBrand;
        System.out.println("Welcome, insert the ammount to pay: ");
        try {
            ammount = scanner.nextInt();
        } catch (InputMismatchException e) {
            throw new CustomException("The inserted data was invalid or not a number", e);
        } catch (NoSuchElementException e) {
            throw new CustomException("You need to insert a numeric value", e);
        } catch (Exception e) {
            throw new CustomException(e.getMessage(), e);
        }

        System.out.println("Insert your credit card brand (VISA, NARA, AMEX): ");
        try {
            brandString = scanner.next().toUpperCase();
            cardBrand = CardBrand.valueOf(brandString);
        } catch (NoSuchElementException e) {
            throw new CustomException("You need to insert your card brand");
        } catch (IllegalArgumentException e) {
            throw new CustomException("The chosen brand is not supported in this system. Try again with a supported brand.", e);
        } catch (Exception e) {
            throw new CustomException(e.getMessage(), e);
        }

        CreditCard sampleCard = sampleCards.get(cardBrand);
        printCardInformation(sampleCard);

        if(ammount > 1000) {
            System.out.println("This operation is invalid because the ammount is higher than 1000");
            return;
        } else {
            System.out.println("This operation is valid because the ammount is lesser than 1000");
        }

        if(sampleCard.isExpired()) {
            System.out.println("Your card is expired, please insert another card with a valid expiration date.");
            return;
        } else {
            System.out.println("Your card has a valid expiration date.");
        }

        Float interest = InterestStrategy.calculateInterest(cardBrand, ammount);
        System.out.println("The calculated interests is: " + interest);
        System.out.println("The ammount to pay is:" + (ammount + interest));


    }

    private static void printCardInformation(CreditCard creditCard) {
        EncryptUtil encryptUtil = new EncryptUtil("Y2lhbzU2Nzg5MTIzNDU2Cg==");
        System.out.println("Here is your card information:");
        System.out.println("Card holder: " + creditCard.getCardHolder());
        System.out.println("Card number" +  encryptUtil.decrypt(creditCard.getCardNumber()));
        System.out.println("Card brand: " + creditCard.getBrand().name());
        System.out.println("Expiration date: " + creditCard.getExpirationDate());
    }
}