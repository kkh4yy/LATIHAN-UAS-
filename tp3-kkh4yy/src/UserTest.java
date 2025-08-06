
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class UserTest {

    User user;

    @BeforeEach
    public void setUp() {
        user = new User(0, "alif", "pass123");
    }

    @Test
    public void testUserCreation() {
        assertEquals(0, user.getId());
        assertEquals("alif", user.getUsername());
        assertEquals("pass123", user.getPassword());
    }

    @Test
    public void testSetAndGetBio() {
        user.setBio("Mahasiswa Informatika 2022");
        assertEquals("Mahasiswa Informatika 2022", user.getBio());
    }

    @Test
    public void testUpdateBio() {
        user.setBio("Hello, I love coding!");
        assertEquals("Hello, I love coding!", user.getBio());
    }

    @Test
    public void testEmptyBio() {
        assertEquals("", user.getBio());
    }

    @Test
    public void testSetAndGetUsername() {
        user.setUsername("zaidan");
        assertEquals("zaidan", user.getUsername());
    }

    @Test
    public void testSetAndGetPassword() {
        user.setPassword("newPassword123");
        assertEquals("newPassword123", user.getPassword());
    }
}
