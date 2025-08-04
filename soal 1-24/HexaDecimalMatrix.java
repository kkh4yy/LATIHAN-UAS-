public class HexaDecimalMatrix extends GenericMatrix<HexaDecimal> {

    @Override
    /** Add two HexaDecimal numbers */
    protected HexaDecimal add(HexaDecimal h1, HexaDecimal h2) {
        return h1.add(h2);
    }

    @Override
    /** Multiply two HexaDecimal numbers */
    protected HexaDecimal multiply(HexaDecimal h1, HexaDecimal h2) {
        return h1.multiply(h2);
    }

    @Override
    /** Specify zero for a HexaDecimal number */
    protected HexaDecimal zero() {
        return new HexaDecimal(0);
    }
}