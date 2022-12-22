package put.io.testing.junit;

import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FailureOrErrorTest {

    // failur
    @Test
    public void test1() {
        assertEquals(2, -2);
    }

    //error
    @Test
    public void test2() {
        assertEquals(5, 10 / 0);
    }

    // poszukiwana klasa to AssertionFailedError
    @Test
    public void test3() {
        try {
            assertEquals(2, -2);
        } catch (AssertionFailedError e) {
            e.printStackTrace();
        }
    }
}
