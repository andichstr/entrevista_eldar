import java.util.Calendar;

/**
 * Class to handle the calculations of every card brand interests
 */
public class InterestStrategy {
    private static final Float MINIMUM_INTEREST = 0.3F;
    private static final Float MAXIMUM_INTEREST = 5F;
    public static Float calculateInterest(CardBrand brand, int amount) {
        Calendar calendar = Calendar.getInstance();
        int day, month, year;
        Float interest = 0f;
        switch (brand) {
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

        if (interest < MINIMUM_INTEREST) return amount * MINIMUM_INTEREST / 100;
        if (interest > MAXIMUM_INTEREST) return amount * MAXIMUM_INTEREST / 100;
        return amount * interest / 100;
    }
}
