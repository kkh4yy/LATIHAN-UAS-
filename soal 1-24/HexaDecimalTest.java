import org.junit.*;
import static org.junit.Assert.*;

public class HexaDecimalTest {
    private HexaDecimal hex1;
    private HexaDecimal hex2;
    private HexaDecimal hex3;
    private HexaDecimal hexZero;

    @Before
    public void setUp() {
        hex1 = new HexaDecimal(10); // A in hex
        hex2 = new HexaDecimal(15); // F in hex
        hex3 = new HexaDecimal("1F"); // 31 in decimal
        hexZero = new HexaDecimal(0);
    }

    @Test
    public void testDefaultConstructor() {
        HexaDecimal hex = new HexaDecimal();
        assertEquals(0, hex.getValue());
        assertEquals("0", hex.toString());
    }

    @Test
    public void testIntConstructor() {
        HexaDecimal hex = new HexaDecimal(255);
        assertEquals(255, hex.getValue());
        assertEquals("FF", hex.toString());
    }

    @Test
    public void testStringConstructor() {
        HexaDecimal hex = new HexaDecimal("A");
        assertEquals(10, hex.getValue());
        assertEquals("A", hex.toString());

        HexaDecimal hex2 = new HexaDecimal("FF");
        assertEquals(255, hex2.getValue());
        assertEquals("FF", hex2.toString());
    }

    @Test(expected = NumberFormatException.class)
    public void testInvalidStringConstructor() {
        new HexaDecimal("XYZ");
    }

    @Test
    public void testGetValue() {
        assertEquals(10, hex1.getValue());
        assertEquals(15, hex2.getValue());
        assertEquals(31, hex3.getValue());
        assertEquals(0, hexZero.getValue());
    }

    @Test
    public void testAdd() {
        HexaDecimal result = hex1.add(hex2);
        assertEquals(25, result.getValue());
        assertEquals("19", result.toString());

        HexaDecimal result2 = hex1.add(hexZero);
        assertEquals(10, result2.getValue());
    }

    @Test
    public void testSubtract() {
        HexaDecimal result = hex2.subtract(hex1);
        assertEquals(5, result.getValue());
        assertEquals("5", result.toString());

        HexaDecimal result2 = hex1.subtract(hex1);
        assertEquals(0, result2.getValue());

        // Test negative result
        HexaDecimal result3 = hex1.subtract(hex2);
        assertEquals(-5, result3.getValue());
    }

    @Test
    public void testMultiply() {
        HexaDecimal result = hex1.multiply(hex2);
        assertEquals(150, result.getValue());
        assertEquals("96", result.toString());

        HexaDecimal result2 = hex1.multiply(hexZero);
        assertEquals(0, result2.getValue());
    }

    @Test
    public void testDivide() {
        HexaDecimal result = hex2.divide(hex1);
        assertEquals(1, result.getValue()); // 15/10 = 1 (integer division)

        HexaDecimal hex20 = new HexaDecimal(20);
        HexaDecimal result2 = hex20.divide(hex1);
        assertEquals(2, result2.getValue());
    }

    @Test(expected = ArithmeticException.class)
    public void testDivideByZero() {
        hex1.divide(hexZero);
    }

    @Test
    public void testToString() {
        assertEquals("A", hex1.toString());
        assertEquals("F", hex2.toString());
        assertEquals("1F", hex3.toString());
        assertEquals("0", hexZero.toString());

        HexaDecimal hex255 = new HexaDecimal(255);
        assertEquals("FF", hex255.toString());
    }

    @Test
    public void testEquals() {
        HexaDecimal hex1Copy = new HexaDecimal(10);
        assertTrue(hex1.equals(hex1Copy));
        assertFalse(hex1.equals(hex2));
        assertFalse(hex1.equals(null));
        assertFalse(hex1.equals("not a HexaDecimal"));
        assertTrue(hex1.equals(hex1)); // reflexive
    }

    @Test
    public void testIntValue() {
        assertEquals(10, hex1.intValue());
        assertEquals(15, hex2.intValue());
        assertEquals(31, hex3.intValue());
        assertEquals(0, hexZero.intValue());
    }

    @Test
    public void testLongValue() {
        assertEquals(10L, hex1.longValue());
        assertEquals(15L, hex2.longValue());
        assertEquals(31L, hex3.longValue());
        assertEquals(0L, hexZero.longValue());
    }

    @Test
    public void testFloatValue() {
        assertEquals(10.0f, hex1.floatValue(), 0.001f);
        assertEquals(15.0f, hex2.floatValue(), 0.001f);
        assertEquals(31.0f, hex3.floatValue(), 0.001f);
        assertEquals(0.0f, hexZero.floatValue(), 0.001f);
    }

    @Test
    public void testDoubleValue() {
        assertEquals(10.0, hex1.doubleValue(), 0.001);
        assertEquals(15.0, hex2.doubleValue(), 0.001);
        assertEquals(31.0, hex3.doubleValue(), 0.001);
        assertEquals(0.0, hexZero.doubleValue(), 0.001);
    }

    @Test
    public void testCompareTo() {
        assertTrue(hex1.compareTo(hex2) < 0); // 10 < 15
        assertTrue(hex2.compareTo(hex1) > 0); // 15 > 10
        assertEquals(0, hex1.compareTo(new HexaDecimal(10))); // 10 == 10
        assertTrue(hex3.compareTo(hex2) > 0); // 31 > 15
        assertTrue(hexZero.compareTo(hex1) < 0); // 0 < 10
    }

    @Test
    public void testLargeNumbers() {
        HexaDecimal large1 = new HexaDecimal(1000);
        HexaDecimal large2 = new HexaDecimal("3E8"); // 1000 in hex
        assertEquals(large1.getValue(), large2.getValue());
        assertEquals("3E8", large1.toString());
    }

    @Test
    public void testEdgeCases() {
        // Test with maximum integer value
        HexaDecimal maxInt = new HexaDecimal(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, maxInt.getValue());

        // Test negative numbers
        HexaDecimal negative = new HexaDecimal(-10);
        assertEquals(-10, negative.getValue());
    }
}