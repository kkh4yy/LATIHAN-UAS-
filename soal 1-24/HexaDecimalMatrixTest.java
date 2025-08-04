import org.junit.*;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class HexaDecimalMatrixTest {
    private HexaDecimalMatrix matrix;
    private HexaDecimal[][] m1;
    private HexaDecimal[][] m2;
    private HexaDecimal[][] m3x2;
    private HexaDecimal[][] m2x3;

    @Before
    public void setUp() {
        matrix = new HexaDecimalMatrix();

        // Create 2x2 matrices for testing
        m1 = new HexaDecimal[2][2];
        m2 = new HexaDecimal[2][2];
        m1[0][0] = new HexaDecimal(1); // 1
        m1[0][1] = new HexaDecimal(2); // 2
        m1[1][0] = new HexaDecimal(3); // 3
        m1[1][1] = new HexaDecimal(4); // 4

        m2[0][0] = new HexaDecimal(5); // 5
        m2[0][1] = new HexaDecimal(6); // 6
        m2[1][0] = new HexaDecimal(7); // 7
        m2[1][1] = new HexaDecimal(8); // 8

        // Create incompatible matrices for testing
        m3x2 = new HexaDecimal[3][2];
        m2x3 = new HexaDecimal[2][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                m3x2[i][j] = new HexaDecimal(i + j + 1);
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                m2x3[i][j] = new HexaDecimal(i + j + 1);
            }
        }
    }

    @Test
    public void testAddMethod() {
        HexaDecimal h1 = new HexaDecimal(10);
        HexaDecimal h2 = new HexaDecimal(5);
        HexaDecimal result = matrix.add(h1, h2);
        assertEquals(15, result.getValue());
    }

    @Test
    public void testMultiplyMethod() {
        HexaDecimal h1 = new HexaDecimal(10);
        HexaDecimal h2 = new HexaDecimal(3);
        HexaDecimal result = matrix.multiply(h1, h2);
        assertEquals(30, result.getValue());
    }

    @Test
    public void testZeroMethod() {
        HexaDecimal zero = matrix.zero();
        assertEquals(0, zero.getValue());
        assertEquals("0", zero.toString());
    }

    @Test
    public void testAddMatrix() {
        HexaDecimal[][] result = matrix.addMatrix(m1, m2);

        assertEquals(6, result[0][0].getValue()); // 1+5
        assertEquals(8, result[0][1].getValue()); // 2+6
        assertEquals(10, result[1][0].getValue()); // 3+7
        assertEquals(12, result[1][1].getValue()); // 4+8
    }

    @Test(expected = RuntimeException.class)
    public void testAddMatrixIncompatibleSize() {
        HexaDecimal[][] incompatible = new HexaDecimal[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                incompatible[i][j] = new HexaDecimal(1);
            }
        }
        matrix.addMatrix(m1, incompatible);
    }

    @Test(expected = RuntimeException.class)
    public void testAddMatrixIncompatibleColumns() {
        matrix.addMatrix(m1, m2x3);
    }

    @Test
    public void testMultiplyMatrix() {
        HexaDecimal[][] result = matrix.multiplyMatrix(m1, m2);

        // [1 2] * [5 6] = [1*5+2*7 1*6+2*8] = [19 22]
        // [3 4] [7 8] [3*5+4*7 3*6+4*8] [43 50]
        assertEquals(19, result[0][0].getValue());
        assertEquals(22, result[0][1].getValue());
        assertEquals(43, result[1][0].getValue());
        assertEquals(50, result[1][1].getValue());
    }

    @Test
    public void testMultiplyMatrixRectangular() {
        // Test 2x3 * 3x2 = 2x2
        HexaDecimal[][] a = new HexaDecimal[2][3];
        HexaDecimal[][] b = new HexaDecimal[3][2];

        // Initialize matrices
        int val = 1;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                a[i][j] = new HexaDecimal(val++);
            }
        }
        val = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                b[i][j] = new HexaDecimal(val++);
            }
        }

        HexaDecimal[][] result = matrix.multiplyMatrix(a, b);
        assertEquals(2, result.length);
        assertEquals(2, result[0].length);
    }

    @Test(expected = RuntimeException.class)
    public void testMultiplyMatrixIncompatible() {
        matrix.multiplyMatrix(m1, m3x2); // 2x2 * 3x2 should fail
    }

    @Test
    public void testHitungElemen() {
        int count = matrix.hitungElemen(m1);
        assertEquals(4, count); // 2x2 matrix has 4 elements

        int count2 = matrix.hitungElemen(m3x2);
        assertEquals(6, count2); // 3x2 matrix has 6 elements

        int count3 = matrix.hitungElemen(m2x3);
        assertEquals(6, count3); // 2x3 matrix has 6 elements
    }

    @Test
    public void testJumlahElemen() {
        HexaDecimal sum = matrix.jumlahElemen(m1);
        assertEquals(10, sum.getValue()); // 1+2+3+4 = 10

        HexaDecimal sum2 = matrix.jumlahElemen(m2);
        assertEquals(26, sum2.getValue()); // 5+6+7+8 = 26
    }

    @Test
    public void testJumlahElemenSingleElement() {
        HexaDecimal[][] single = new HexaDecimal[1][1];
        single[0][0] = new HexaDecimal(42);

        HexaDecimal sum = matrix.jumlahElemen(single);
        assertEquals(42, sum.getValue());
    }

    @Test
    public void testJumlahElemenWithZero() {
        HexaDecimal[][] withZero = new HexaDecimal[2][2];
        withZero[0][0] = new HexaDecimal(0);
        withZero[0][1] = new HexaDecimal(5);
        withZero[1][0] = new HexaDecimal(10);
        withZero[1][1] = new HexaDecimal(0);

        HexaDecimal sum = matrix.jumlahElemen(withZero);
        assertEquals(15, sum.getValue()); // 0+5+10+0 = 15
    }

    @Test
    public void testPrintResult() {
        // Capture console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            HexaDecimal[][] result = matrix.addMatrix(m1, m2);
            matrix.printResult(m1, m2, result, '+');

            String output = outContent.toString();
            assertTrue(output.contains("+")); // Should contain the operator
            assertTrue(output.length() > 0); // Should have some output
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void testMatrixOperationsWithLargeNumbers() {
        HexaDecimal[][] large1 = new HexaDecimal[2][2];
        HexaDecimal[][] large2 = new HexaDecimal[2][2];

        large1[0][0] = new HexaDecimal(255); // FF
        large1[0][1] = new HexaDecimal(256); // 100
        large1[1][0] = new HexaDecimal(15); // F
        large1[1][1] = new HexaDecimal(16); // 10

        large2[0][0] = new HexaDecimal(1);
        large2[0][1] = new HexaDecimal(1);
        large2[1][0] = new HexaDecimal(1);
        large2[1][1] = new HexaDecimal(1);

        HexaDecimal[][] result = matrix.addMatrix(large1, large2);
        assertEquals(256, result[0][0].getValue()); // 255+1
        assertEquals(257, result[0][1].getValue()); // 256+1
        assertEquals(16, result[1][0].getValue()); // 15+1
        assertEquals(17, result[1][1].getValue()); // 16+1
    }

    @Test
    public void testEmptyMatrix() {
        HexaDecimal[][] empty = new HexaDecimal[0][0];
        // This should not crash, but behavior may vary
        try {
            int count = matrix.hitungElemen(empty);
            assertEquals(0, count);
        } catch (Exception e) {
            // Some implementations might throw exception for empty matrix
            assertTrue(true); // Accept either behavior
        }
    }

    @Test
    public void testSingleRowMatrix() {
        HexaDecimal[][] singleRow = new HexaDecimal[1][3];
        singleRow[0][0] = new HexaDecimal(1);
        singleRow[0][1] = new HexaDecimal(2);
        singleRow[0][2] = new HexaDecimal(3);

        int count = matrix.hitungElemen(singleRow);
        assertEquals(3, count);

        HexaDecimal sum = matrix.jumlahElemen(singleRow);
        assertEquals(6, sum.getValue()); // 1+2+3
    }

    @Test
    public void testSingleColumnMatrix() {
        HexaDecimal[][] singleCol = new HexaDecimal[3][1];
        singleCol[0][0] = new HexaDecimal(1);
        singleCol[1][0] = new HexaDecimal(2);
        singleCol[2][0] = new HexaDecimal(3);

        int count = matrix.hitungElemen(singleCol);
        assertEquals(3, count);

        HexaDecimal sum = matrix.jumlahElemen(singleCol);
        assertEquals(6, sum.getValue()); // 1+2+3
    }
}