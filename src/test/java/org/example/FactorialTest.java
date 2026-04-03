package org.example;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class FactorialTest {
    @Test public void testFactorialZero() { assertEquals(Factorial.calculate(0), 1); }
    @Test public void testFactorialOne() { assertEquals(Factorial.calculate(1), 1); }
    @Test public void testFactorialFive() { assertEquals(Factorial.calculate(5), 120); }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFactorialNegative() { Factorial.calculate(-1); }
}