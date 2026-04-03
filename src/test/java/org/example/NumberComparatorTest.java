package org.example;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class NumberComparatorTest {
    @Test public void testGreater() { assertEquals(NumberComparator.compare(5, 3), "5 больше 3"); }
    @Test public void testLess() { assertEquals(NumberComparator.compare(3, 5), "3 меньше 5"); }
    @Test public void testEqual() { assertEquals(NumberComparator.compare(4, 4), "4 равно 4"); }
}