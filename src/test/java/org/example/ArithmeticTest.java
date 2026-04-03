package org.example;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class ArithmeticTest {
    @Test public void testAdd() { assertEquals(Arithmetic.add(3, 4), 7); }
    @Test public void testSubtract() { assertEquals(Arithmetic.subtract(5, 3), 2); }
    @Test public void testMultiply() { assertEquals(Arithmetic.multiply(3, 4), 12); }
    @Test public void testDivide() { assertEquals(Arithmetic.divide(6, 3), 2.0, 0.0001); }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDivideByZero() { Arithmetic.divide(5, 0); }
}