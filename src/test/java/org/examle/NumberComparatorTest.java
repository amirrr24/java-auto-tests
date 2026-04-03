package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NumberComparatorTest {
    @Test void testGreater() { assertEquals("5 больше 3", NumberComparator.compare(5, 3)); }
    @Test void testLess() { assertEquals("3 меньше 5", NumberComparator.compare(3, 5)); }
    @Test void testEqual() { assertEquals("4 равно 4", NumberComparator.compare(4, 4)); }
}