public class HexaDecimalMatrixTest {
    public static void main(String[] args) {
        // Create two HexaDecimal arrays m1 and m2
        HexaDecimal[][] m1 = new HexaDecimal[3][3];
        HexaDecimal[][] m2 = new HexaDecimal[3][3];

        // Fill matrices with HexaDecimal values
        // m1: menggunakan pola (i+1) * (j+1) untuk membuat nilai yang bervariasi
        // m2: menggunakan pola (i+j+1) untuk membuat nilai yang berbeda
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[0].length; j++) {
                m1[i][j] = new HexaDecimal((i + 1) * (j + 1));
                m2[i][j] = new HexaDecimal(i + j + 1);
            }
        }

        // Create an instance of HexaDecimalMatrix
        HexaDecimalMatrix hexMatrix = new HexaDecimalMatrix();

        System.out.println("Matrix m1:");
        printMatrix(m1);

        System.out.println("\nMatrix m2:");
        printMatrix(m2);

        System.out.println("\nm1 + m2 is ");
        GenericMatrix.printResult(
                m1, m2, hexMatrix.addMatrix(m1, m2), '+');

        System.out.println("\nm1 * m2 is ");
        GenericMatrix.printResult(
                m1, m2, hexMatrix.multiplyMatrix(m1, m2), '*');

        // Test additional methods from GenericMatrix
        System.out.println("\nJumlah elemen dalam m1: " + hexMatrix.hitungElemen(m1));
        System.out.println("Total nilai semua elemen m1: " + hexMatrix.jumlahElemen(m1));
    }

    // Helper method to print matrix in a cleaner format
    private static void printMatrix(HexaDecimal[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
}