package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArithmeticTest {
    @Test void testAdd() { assertEquals(7, Arithmetic.add(3, 4)); }
    @Test void testSubtract() { assertEquals(2, Arithmetic.subtract(5, 3)); }
    @Test void testMultiply() { assertEquals(12, Arithmetic.multiply(3, 4)); }
    @Test void testDivide() { assertEquals(2.0, Arithmetic.divide(6, 3)); }
    @Test void testDivideByZero() {
        assertThrows(IllegalArgumentException.class, () -> Arithmetic.divide(5, 0));
    }
}