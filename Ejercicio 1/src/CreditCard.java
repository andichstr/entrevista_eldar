import java.util.Date;
import java.util.List;
import java.util.Objects;

public class CreditCard {
    private CardBrand brand;
    private byte[] cardNumber;
    private String cardHolder;
    private Date expirationDate;

    public CreditCard(CardBrand brand, byte[] cardNumber, String cardHolder, Date expirationDate) {
        this.brand = brand;
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expirationDate = expirationDate;
    }

    public CardBrand getBrand() {
        return brand;
    }

    public byte[] getCardNumber() {
        return cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setBrand(CardBrand brand) {
        this.brand = brand;
    }

    public void setCardNumber(byte[] cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isExpired() {
        return new Date().getTime() > this.expirationDate.getTime();
    }

    public boolean isEqual(CreditCard creditCard) {
        return Objects.equals(creditCard.getCardHolder(), this.getCardHolder()) &&
                Objects.equals(creditCard.getBrand(), this.getBrand()) &&
                Objects.equals(creditCard.getExpirationDate(), this.getExpirationDate()) &&
                Objects.equals(creditCard.getCardNumber(), this.getCardNumber());

    }
}
