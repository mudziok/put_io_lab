package put.io.testing.audiobooks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AudiobookPriceCalculatorTest {
    private final AudiobookPriceCalculator calculator = new AudiobookPriceCalculator();

    @Test
    public void testCalculateForSubscriber() {
        Customer customer = new Customer("Słoń", Customer.LoyaltyLevel.STANDARD, true);
        Audiobook audiobook = new Audiobook("Harrassment Architecture", 10.0);

        double price = calculator.calculate(customer, audiobook);

        assertEquals(0.0, price);
    }

    @Test
    public void testCalculateForNonSubscriberLoyaltyLevelSilver() {
        Customer customer = new Customer("Małpa", Customer.LoyaltyLevel.SILVER, false);
        Audiobook audiobook = new Audiobook("Kafka on the shore", 10.0);

        double price = calculator.calculate(customer, audiobook);

        assertEquals(9.0, price);
    }

    @Test
    public void testCalculateForNonSubscriberLoyaltyLevelGold() {
        Customer customer = new Customer("Miś", Customer.LoyaltyLevel.GOLD, false);
        Audiobook audiobook = new Audiobook("Nie-Boska komedia", 10.0);

        double price = calculator.calculate(customer, audiobook);

        assertEquals(8.0, price);
    }

    @Test
    public void testCalculateForNonSubscriberLoyaltyLevelNone() {
        Customer customer = new Customer("Żaba", Customer.LoyaltyLevel.STANDARD, false);
        Audiobook audiobook = new Audiobook("Przemiana", 10.0);

        double price = calculator.calculate(customer, audiobook);

        assertEquals(10.0, price);
    }
}