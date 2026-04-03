package org.example;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class TriangleAreaTest {
    @Test public void testRightTriangle() { assertEquals(TriangleArea.calculate(3, 4, 5), 6.0, 0.0001); }
    @Test public void testEquilateralTriangle() { assertEquals(TriangleArea.calculate(3, 3, 3), 3.8971, 0.0001); }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testInvalidTriangle() { TriangleArea.calculate(1, 1, 3); }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNegativeSide() { TriangleArea.calculate(-3, 4, 5); }
}