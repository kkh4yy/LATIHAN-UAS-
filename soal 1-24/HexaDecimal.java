public class HexaDecimal extends Number implements Comparable<HexaDecimal> {
    private static final long serialVersionUID = 1L;
    private int value; // Menyimpan nilai dalam bentuk decimal

    /** Construct a HexaDecimal with default value 0 */
    public HexaDecimal() {
        this.value = 0;
    }

    /** Construct a HexaDecimal with specified integer value */
    public HexaDecimal(int value) {
        this.value = value;
    }

    /** Construct a HexaDecimal from hex string */
    public HexaDecimal(String hexString) {
        this.value = Integer.parseInt(hexString, 16);
    }

    /** Get the decimal value */
    public int getValue() {
        return value;
    }

    /** Add two HexaDecimal numbers */
    public HexaDecimal add(HexaDecimal other) {
        return new HexaDecimal(this.value + other.value);
    }

    /** Subtract a HexaDecimal number from this */
    public HexaDecimal subtract(HexaDecimal other) {
        return new HexaDecimal(this.value - other.value);
    }

    /** Multiply two HexaDecimal numbers */
    public HexaDecimal multiply(HexaDecimal other) {
        return new HexaDecimal(this.value * other.value);
    }

    /** Divide this HexaDecimal by another */
    public HexaDecimal divide(HexaDecimal other) {
        if (other.value == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return new HexaDecimal(this.value / other.value);
    }

    @Override
    public String toString() {
        return Integer.toHexString(value).toUpperCase();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof HexaDecimal) {
            return this.value == ((HexaDecimal) other).value;
        }
        return false;
    }

    @Override
    public int intValue() {
        return value;
    }

    @Override
    public long longValue() {
        return value;
    }

    @Override
    public float floatValue() {
        return value;
    }

    @Override
    public double doubleValue() {
        return value;
    }

    @Override
    public int compareTo(HexaDecimal other) {
        return Integer.compare(this.value, other.value);
    }
}