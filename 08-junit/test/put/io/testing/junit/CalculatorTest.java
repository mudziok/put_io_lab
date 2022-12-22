package put.io.testing.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    private Calculator calculator;

    // 3.1 Klasa calculator jest swego rodzaju namespace dla funkcji czyli nie posiada stanu
    // zmiana na beforeAll nic by nie zmieniła
    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void testAdd() {
        assertEquals(5, calculator.add(2, 3));
        assertEquals(-5, calculator.add(-2, -3));
        assertEquals(0, calculator.add(2, -2));
    }

    @Test
    public void testMultiply() {
        assertEquals(6, calculator.multiply(2, 3));
        assertEquals(6, calculator.multiply(-2, -3));
        assertEquals(-6, calculator.multiply(-2, 3));
        assertEquals(-6, calculator.multiply(2, -3));
    }

    @Test
    public void testAddPositiveNumbers() {
        assertEquals(5, calculator.addPositiveNumbers(2, 3));
        assertThrows(IllegalArgumentException.class, () -> calculator.addPositiveNumbers(-2, 3));
        assertThrows(IllegalArgumentException.class, () -> calculator.addPositiveNumbers(2, -3));
        assertThrows(IllegalArgumentException.class, () -> calculator.addPositiveNumbers(-2, -3));
    }
}

