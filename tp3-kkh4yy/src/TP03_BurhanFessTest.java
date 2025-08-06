
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.*;

public class TP03_BurhanFessTest {

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void setUpStreams() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @AfterEach
    public void restoreStreams() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Test
    public void testCariId_found() {
        User[] users = {
                new User(0, "alif", "pw"),
                new User(1, "zaidan", "pw"),
                new User(2, "grady", "pw")
        };
        assertEquals(1, TP03_BurhanFess.cariId(users, "zaidan"));
        assertEquals(2, TP03_BurhanFess.cariId(users, "GRADY"));
    }

    @Test
    public void testCariId_notFound() {
        User[] users = {
                new User(0, "alif", "pw"),
                new User(1, "zaidan", "pw")
        };
        assertEquals(-1, TP03_BurhanFess.cariId(users, "nonexistent"));
    }

    @Test
    public void testMainFullExecution() {
        String input = String.join("\n",
                "3", // jumlah user
                "alif", "pass1",
                "budi", "pass2",
                "caca", "pass3",
                "3", // jumlah fess
                "10", "5", "15", // delay
                "3", // jumlah follow
                "alif", "budi",
                "budi", "caca",
                "caca", "alif") + "\n";

        provideInput(input);
        TP03_BurhanFess.main(new String[] {});

        String output = testOut.toString();
        assertTrue(output.contains("alif"));
        assertTrue(output.contains("budi"));
        assertTrue(output.contains("caca"));
        assertTrue(output.contains("berhasil mem-follow"));
        assertTrue(output.contains("Daftar mutual burhanFess"));
        assertTrue(output.contains("Followers:"));
        assertTrue(output.contains("Following:"));
    }
}
