package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FactorialTest {
    @Test void testFactorialZero() { assertEquals(1, Factorial.calculate(0)); }
    @Test void testFactorialOne() { assertEquals(1, Factorial.calculate(1)); }
    @Test void testFactorialFive() { assertEquals(120, Factorial.calculate(5)); }
    @Test void testFactorialNegative() {
        assertThrows(IllegalArgumentException.class, () -> Factorial.calculate(-1));
    }
}