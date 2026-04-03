package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TriangleAreaTest {
    @Test void testRightTriangle() { assertEquals(6.0, TriangleArea.calculate(3, 4, 5), 0.0001); }
    @Test void testEquilateralTriangle() { assertEquals(3.8971, TriangleArea.calculate(3, 3, 3), 0.0001); }
    @Test void testInvalidTriangle() {
        assertThrows(IllegalArgumentException.class, () -> TriangleArea.calculate(1, 1, 3));
    }
    @Test void testNegativeSide() {
        assertThrows(IllegalArgumentException.class, () -> TriangleArea.calculate(-3, 4, 5));
    }
}